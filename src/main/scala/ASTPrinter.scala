package com.vladimir.nik.print.plugin.printer

import com.vladimir.nik.print.plugin.PrintPlugin
import java.io.PrintWriter
import scala.reflect.internal.Flags._
import scala.tools.nsc
import scala.tools.nsc.ast.Printers
import nsc.Global

/**
 * Created with IntelliJ IDEA.
 * User: vova
 * Date: 7/15/13
 * Time: 2:38 AM
 * To change this template use File | Settings | File Templates.
 */

class ASTPrinters(val global: Global, val out: PrintWriter) {

  import global._

class ASTPrinter extends global.TreePrinter(out) {

  //this methods are here because they are private
  private def symFn[T](tree: Tree, f: Symbol => T, orElse: => T): T = tree.symbol match {
    case null | NoSymbol  => orElse
    case sym              => f(sym)
  }
  private def ifSym(tree: Tree, p: Symbol => Boolean) = symFn(tree, p, false)

  private var currentOwner: Symbol = NoSymbol
  private var selectorType: Type = NoType

  override def printTree(tree: Tree) {
    print("Hello, Vova!")
    tree match {
      case EmptyTree =>
        print("<empty>")

      case ClassDef(mods, name, tparams, impl) =>
        printAnnotations(tree)
        printModifiers(tree, mods)
        val word =
          if (mods.isTrait) "trait"
          else if (ifSym(tree, _.isModuleClass)) "object"
          else "class"

        print(word, " ", symName(tree, name))
        printTypeParams(tparams)
        print(if (mods.isDeferred) " <: " else " extends ", impl)

      case PackageDef(packaged, stats) =>
        printAnnotations(tree)
        print("package ", packaged); printColumn(stats, " {", ";", "}")

      case ModuleDef(mods, name, impl) =>
        printAnnotations(tree)
        printModifiers(tree, mods);
        print("object " + symName(tree, name), " extends ", impl)

      case ValDef(mods, name, tp, rhs) =>
        printAnnotations(tree)
        printModifiers(tree, mods)
        print(if (mods.isMutable) "var " else "val ", symName(tree, name))
        printOpt(": ", tp)
        if (!mods.isDeferred)
          print(" = ", if (rhs.isEmpty) "_" else rhs)

      case DefDef(mods, name, tparams, vparamss, tp, rhs) =>
        printAnnotations(tree)
        printModifiers(tree, mods)
        print("def " + symName(tree, name))
        printTypeParams(tparams); vparamss foreach printValueParams
        printOpt(": ", tp); printOpt(" = ", rhs)

      case TypeDef(mods, name, tparams, rhs) =>
        if (mods hasFlag (PARAM | DEFERRED)) {
          printAnnotations(tree)
          printModifiers(tree, mods); print("type "); printParam(tree)
        } else {
          printAnnotations(tree)
          printModifiers(tree, mods); print("type " + symName(tree, name))
          printTypeParams(tparams); printOpt(" = ", rhs)
        }

      case LabelDef(name, params, rhs) =>
        print(symName(tree, name)); printLabelParams(params); printBlock(rhs)

      case Import(expr, selectors) =>
        // Is this selector remapping a name (i.e, {name1 => name2})
        def isNotRemap(s: ImportSelector) : Boolean = (s.name == nme.WILDCARD || s.name == s.rename)
        def selectorToString(s: ImportSelector): String = {
          val from = quotedName(s.name)
          if (isNotRemap(s)) from
          else from + "=>" + quotedName(s.rename)
        }
        print("import ", backquotedPath(expr), ".")
        selectors match {
          case List(s) =>
            // If there is just one selector and it is not remapping a name, no braces are needed
            if (isNotRemap(s)) print(selectorToString(s))
            else print("{", selectorToString(s), "}")
          // If there is more than one selector braces are always needed
          case many =>
            print(many.map(selectorToString).mkString("{", ", ", "}"))
        }

      case Template(parents, self, body) =>
        val currentOwner1 = currentOwner
        if (tree.symbol != NoSymbol) currentOwner = tree.symbol.owner
        //          if (parents exists isReferenceToAnyVal) {
        //            print("AnyVal")
        //          }
        //          else {
        printRow(parents, " with ")
        if (!body.isEmpty) {
          if (self.name != nme.WILDCARD) {
            print(" { ", self.name); printOpt(": ", self.tpt); print(" => ")
          } else if (!self.tpt.isEmpty) {
            print(" { _ : ", self.tpt, " => ")
          } else {
            print(" {")
          }
          printColumn(body, "", ";", "}")
        }
        //          }
        currentOwner = currentOwner1

      case Block(stats, expr) =>
        printColumn(stats ::: List(expr), "{", ";", "}")

      case Match(selector, cases) =>
        val selectorType1 = selectorType
        selectorType = selector.tpe
        print(selector); printColumn(cases, " match {", "", "}")
        selectorType = selectorType1

      case CaseDef(pat, guard, body) =>
        print("case ")
        def patConstr(pat: Tree): Tree = pat match {
          case Apply(fn, args) => patConstr(fn)
          case _ => pat
        }
        if (showOuterTests &&
          needsOuterTest(
            patConstr(pat).tpe.finalResultType, selectorType, currentOwner))
          print("???")
        print(pat); printOpt(" if ", guard)
        print(" => ", body)

      case Alternative(trees) =>
        printRow(trees, "(", "| ", ")")

      case Star(elem) =>
        print("(", elem, ")*")

      case Bind(name, t) =>
        print("(", symName(tree, name), " @ ", t, ")")

      case UnApply(fun, args) =>
        print(fun, " <unapply> "); printRow(args, "(", ", ", ")")

      case ArrayValue(elemtpt, trees) =>
        print("Array[", elemtpt); printRow(trees, "]{", ", ", "}")

      case Function(vparams, body) =>
        print("("); printValueParams(vparams); print(" => ", body, ")")
        if (printIds && tree.symbol != null) print("#"+tree.symbol.id)

      case Assign(lhs, rhs) =>
        print(lhs, " = ", rhs)

      case AssignOrNamedArg(lhs, rhs) =>
        print(lhs, " = ", rhs)

      case If(cond, thenp, elsep) =>
        print("if (", cond, ")"); indent; println()
        print(thenp); undent
        if (!elsep.isEmpty) {
          println(); print("else"); indent; println(); print(elsep); undent
        }

      case Return(expr) =>
        print("return ", expr)

      case Try(block, catches, finalizer) =>
        print("try "); printBlock(block)
        if (!catches.isEmpty) printColumn(catches, " catch {", "", "}")
        printOpt(" finally ", finalizer)

      case Throw(expr) =>
        print("throw ", expr)

      case New(tpe) =>
        print("new ", tpe)

      case Typed(expr, tp) =>
        print("(", expr, ": ", tp, ")")

      case TypeApply(fun, targs) =>
        print(fun); printRow(targs, "[", ", ", "]")

      case Apply(fun, vargs) =>
        print(fun); printRow(vargs, "(", ", ", ")")

      case ApplyDynamic(qual, vargs) =>
        print("<apply-dynamic>(", qual, "#", tree.symbol.nameString)
        printRow(vargs, ", (", ", ", "))")

      case Super(This(qual), mix) =>
        if (!qual.isEmpty || tree.symbol != NoSymbol) print(symName(tree, qual) + ".")
        print("super")
        if (!mix.isEmpty)
          print("[" + mix + "]")

      case Super(qual, mix) =>
        print(qual, ".super")
        if (!mix.isEmpty)
          print("[" + mix + "]")

      case This(qual) =>
        if (!qual.isEmpty) print(symName(tree, qual) + ".")
        print("this")

      case Select(qual @ New(tpe), name) if (!settings.debug.value) =>
        print(qual)

      case Select(qualifier, name) =>
        print(backquotedPath(qualifier), ".", symName(tree, name))

      case id @ Ident(name) =>
        val str = symName(tree, name)
        print( if (id.isBackquoted) "`" + str + "`" else str )

      case Literal(x) =>
        print(x.escapedStringValue)

      case tt: TypeTree =>
        if ((tree.tpe eq null) || (doPrintPositions && tt.original != null)) {
          if (tt.original != null) print("<type: ", tt.original, ">")
          else print("<type ?>")
        } else if ((tree.tpe.typeSymbol ne null) && tree.tpe.typeSymbol.isAnonymousClass) {
          print(tree.tpe.typeSymbol.toString)
        } else {
          print(tree.tpe.toString)
        }

      case Annotated(Apply(Select(New(tpt), nme.CONSTRUCTOR), args), tree) =>
        def printAnnot() {
          print("@", tpt)
          if (!args.isEmpty)
            printRow(args, "(", ",", ")")
        }
        print(tree, if (tree.isType) " " else ": ")
        printAnnot()

      case SingletonTypeTree(ref) =>
        print(ref, ".type")

      case SelectFromTypeTree(qualifier, selector) =>
        print(qualifier, "#", symName(tree, selector))

      case CompoundTypeTree(templ) =>
        print(templ)

      case AppliedTypeTree(tp, args) =>
        print(tp); printRow(args, "[", ", ", "]")

      case TypeBoundsTree(lo, hi) =>
        printOpt(" >: ", lo); printOpt(" <: ", hi)

      case ExistentialTypeTree(tpt, whereClauses) =>
        print(tpt);
        printColumn(whereClauses, " forSome { ", ";", "}")

      // SelectFromArray is no longer visible in reflect.internal.
      // eliminated until we figure out what we will do with both Printers and
      // SelectFromArray.
      //          case SelectFromArray(qualifier, name, _) =>
      //          print(qualifier); print(".<arr>"); print(symName(tree, name))

      case tree =>
        xprintTree(this, tree)
    }
    if (printTypes && tree.isTerm && !tree.isEmpty) {
      print("{", if (tree.tpe eq null) "<null>" else tree.tpe.toString, "}")
    }
  }




  override def print(args: Any*): Unit = args foreach {
    case tree: Tree =>
      printPosition(tree)
      printTree(
        if (tree.isDef && tree.symbol != NoSymbol && tree.symbol.isInitialized) {
          tree match {
            case ClassDef(_, _, _, impl @ Template(ps, emptyValDef, body))
              if (tree.symbol.thisSym != tree.symbol) =>
              ClassDef(tree.symbol, Template(ps, ValDef(tree.symbol.thisSym), body))
            case ClassDef(_, _, _, impl)           => ClassDef(tree.symbol, impl)
            case ModuleDef(_, _, impl)             => ModuleDef(tree.symbol, impl)
            case ValDef(_, _, _, rhs)              => ValDef(tree.symbol, rhs)
            case DefDef(_, _, _, vparamss, _, rhs) => DefDef(tree.symbol, vparamss, rhs)
            case TypeDef(_, _, _, rhs)             => TypeDef(tree.symbol, rhs)
            case _ => tree
          }
        } else tree)
    case unit: CompilationUnit =>
      print("// Scala source: " + unit.source + "\n")
      if (unit.body == null) print("<null>")
      else { print(unit.body); println() }
      println()
      out.flush()
    case name: Name =>
      print(quotedName(name))
    case arg =>
      out.print(if (arg == null) "null" else arg.toString)
  }

  //print from internal
//  override def print(args: Any*): Unit = args foreach {
//    case tree: Tree =>
//      printPosition(tree)
//      printTree(tree)
//    case name: Name =>
//      print(quotedName(name))
//    case arg =>
//      out.print(if (arg == null) "null" else arg.toString)
//  }

  }
}
