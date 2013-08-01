package com.vladimir.nik.print.plugin.printer

import com.vladimir.nik.print.plugin.PrintPlugin
import java.io.{StringWriter, PrintWriter}
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

  //TODO refactor show
  //TODO not optimal - bad solution
  //don't forget this method after ASTPrinter moving!!!
  def show(what: Any, generic: Boolean) = {

    val buffer = new StringWriter()
    val writer = new PrintWriter(buffer)

    val printers = new ASTPrinters(global, writer)
    var printer = new printers.ASTPrinter


    printer.print(what)
    writer.flush()
    buffer.toString
  }

  class ASTPrinter extends global.TreePrinter(out) {

    //this methods are here because they are private
    private def symFn[T](tree: Tree, f: Symbol => T, orElse: => T): T = tree.symbol match {
      case null | NoSymbol => orElse
      case sym => f(sym)
    }

    private def ifSym(tree: Tree, p: Symbol => Boolean) = symFn(tree, p, false)

    private var currentOwner: Symbol = NoSymbol
    private var selectorType: Type = NoType

    override def printModifiers(tree: Tree, mods: Modifiers): Unit = printModifiers(tree, mods, false)

    def printModifiers(tree: Tree, mods: Modifiers, isCtr: Boolean): Unit = printFlags(
      if (tree.symbol == NoSymbol) mods.flags else tree.symbol.flags, "" + (
        if (tree.symbol == NoSymbol) mods.privateWithin
        else if (tree.symbol.hasAccessBoundary) tree.symbol.privateWithin.name
        else ""
        ), isCtr
    )

    //to skip all annotations
    override def printFlags(flags: Long, privateWithin: String) {
      printFlags(flags, privateWithin, false)
    }

    def printFlags(flags: Long, privateWithin: String, isCtr: Boolean) {
      val base = PROTECTED | OVERRIDE | PRIVATE | ABSTRACT | FINAL | SEALED | CASE | LAZY | LOCAL
      val ASTPrinterFlags = if (isCtr) base else base | IMPLICIT

      var mask: Long = if (settings.debug.value) -1L else ASTPrinterFlags
      //    var mask: Long = if (settings.debug.value) -1L else PrintableFlags
      //var mask: Long = 0
      val s = flagsToString(flags & mask, privateWithin)
      if (s != "") print(s + " ")
    }

    def printConstrParams(ts: List[ValDef], isConstr: Boolean) {
      print("(")
      if (!ts.isEmpty) printFlags(ts.head.mods.flags & IMPLICIT, "")
      printSeq(ts) {
        x => printParam(x, true)
      } {
        print(", ")
      }
      print(")")
    }

    override def printValueParams(ts: List[ValDef]) {
      print("(")
      //TODO when we have constructor with implicit params first group of parameters
      //always are not implicits - even if there are no other parameters ()(implicit val a:String ...)
      if (!ts.isEmpty) printFlags(ts.head.mods.flags & IMPLICIT, "")
      printSeq(ts) {
        printParam
      } {
        print(", ")
      }
      print(")")
    }

    def printParam(tree: Tree, isConstr: Boolean) {
      tree match {
        case ValDef(mods, name, tp, rhs) =>
          printPosition(tree)
          printAnnotations(tree)
          if (isConstr) {
            printModifiers(tree, mods, isConstr)
          }
          print(if (mods.isMutable && isConstr) "var " else if (isConstr) "val " else "", symName(tree, name));
          printOpt(": ", tp);
          printOpt(" = ", rhs)
        case TypeDef(mods, name, tparams, rhs) =>
          printPosition(tree)
          print(symName(tree, name))
          printTypeParams(tparams);
          print(rhs)
      }
    }

    override def printParam(tree: Tree) {
      printParam(tree, false)
    }

    override def printAnnotations(tree: Tree) {
      // SI-5885: by default this won't print annotations of not yet initialized symbols
      //    val annots = tree.symbol.annotations match {
      //      case Nil  => tree.asInstanceOf[MemberDef].mods.annotations
      //      case anns => anns
      //    }

      val annots = tree.asInstanceOf[MemberDef].mods.annotations
//      annots foreach {
//        annot =>
//          val n = annot.find {
//            case New(Ident(_)) => true
//            case _ => false
//          } getOrElse (EmptyTree)
//          if (n.isEmpty) print("@",annot)//print("@" + annot + " ")
//          else {
//            val New(id@Ident(name)) = n
//            val Apply(_, params) = annot
//            print("@", symName(id, name))
//            if (!params.isEmpty) {
//              print("(")
//              printSeq(params) {
//                print(_)
//              } {
//                print(", ")
//              }
//              print(")")
//            }
//            print(" ")
//          }
//      }
      annots foreach {
        case Apply(Select(New(tree), p), args) => val ap = Apply(tree, args)
          print("@", ap, " ")
        case ann => print("@" + ann + " ")
      }

      //    annots foreach (annot => print("@"+annot+" "))
    }

    override def printTypeParams(ts: List[TypeDef]) {
      if (!ts.isEmpty) {
        print("["); printSeq(ts){ t =>
          printAnnotations(t)
          if (t.mods.hasFlag(CONTRAVARIANT)) {
            print("-")
          } else if (t.mods.hasFlag(COVARIANT)) {
            print("+")
          }
          printParam(t)
        }{print(", ")}; print("]")
      }
    }

    def backquotedPath(t: Tree): String = {
      t match {
        case Select(qual, name) if name.isTermName  => "%s.%s".format(backquotedPath(qual), symName(t, name))
        case Select(qual, name) if name.isTypeName  => "%s#%s".format(backquotedPath(qual), symName(t, name))
        case Ident(name)                            => symName(t, name)
        case _                                      => show(t, true)
      }
    }

    override def printTree(tree: Tree) {
      tree match {
        case EmptyTree =>
          //print("<empty>")
          print("")

        //TODO - method to get primary constructor
        //TODO - method to get auxilary constructor
        //TODO - method to get defdef

        case ClassDef(mods, name, tparams, impl) =>
          //System.out.println("showRaw tree: " + showRaw(tree) + "\n")
          //System.out.println("show tree: " + show(tree) + "\n")

          printAnnotations(tree)
          printModifiers(tree, mods)
          val word =
            if (mods.isTrait) "trait"
            else if (ifSym(tree, _.isModuleClass)) "object"
            else "class"

          print(word, " ", symName(tree, name))

          printTypeParams(tparams)

          val Template(List(_*), self, methods) = impl

          if (!mods.isTrait) {
            val templateVals = methods collect {
              case ValDef(mods, name, _, _) => (name, mods)
            }

            val primaryConstr = methods collectFirst {
              case dd: DefDef if dd.name == nme.CONSTRUCTOR => dd
            } getOrElse (null)

            val cstrMods = if (primaryConstr != null) primaryConstr.mods else null
            val ctparams = if (primaryConstr != null) primaryConstr.tparams else null
            val vparamss = if (primaryConstr != null) primaryConstr.vparamss else null
            val tp = if (primaryConstr != null) primaryConstr.tpt else null
            val rhs = if (primaryConstr != null) primaryConstr.rhs else null


            //TODO combine modifiers from vals and defs
            //TODO remove duplicate annotations
            val printParamss = if (primaryConstr != null) {
              vparamss map {
                vparams =>
                  if (vparams.isEmpty) vparams
                  else vparams map {
                    vparam =>
                    val templateVal = templateVals find {
                      _._1 == vparam.name
                    } getOrElse null
                    if (templateVal != null)
                      ValDef(Modifiers(vparam.mods.flags | templateVal._2.flags, vparam.mods.privateWithin, (vparam.mods.annotations ::: templateVal._2.annotations) distinct), vparam.name, vparam.tpt, vparam.rhs)
                      else vparam
                  }
              }
            } else null
//            val printParamss2 = if (primaryConstr != null) (vparamss(0), templateVals).zipped.map((x, y) =>
//              ValDef(Modifiers(x.mods.flags | y._2.flags, x.mods.privateWithin, (x.mods.annotations ::: y._2.annotations) distinct), x.name, x.tpt, x.rhs))
//            else null
//            System.out.println("printParamss: " + printParamss)
//            System.out.println("printParamss2: " + printParamss2)

            if (primaryConstr != null) {
              //constructor's modifier
              if (cstrMods.hasFlag(AccessFlags)) {
                print(" ")
                printModifiers(primaryConstr, cstrMods)
              } else print(" ")

              //constructor's params
              printParamss foreach { printParams =>
                if (!printParams.isEmpty || cstrMods.hasFlag(AccessFlags)) {
                  printConstrParams(printParams, true)
                  print(" ")
                }
              }
            } else print(" ")
          } else print(" ")

          print(if (mods.isDeferred) "<: " else "extends ", impl)

        case PackageDef(packaged, stats) =>
          packaged match {
            case Ident(name) if name == nme.EMPTY_PACKAGE_NAME =>
              //printColumn(stats, " {", ";", "}")
              printSeq(stats) {
                print(_)
              } {
                print(";");
                println()
              };
            case _ =>
              printAnnotations(tree)
              print("package ", packaged);
              printColumn(stats, " {", ";", "}")
          }

        case ModuleDef(mods, name, impl) =>
          printAnnotations(tree)
          printModifiers(tree, mods);
          print("object " + symName(tree, name), " extends ", impl)

        case vd@ValDef(mods, name, tp, rhs) =>
          printAnnotations(tree)
          printModifiers(tree, mods)
          print(if (mods.isMutable) "var " else "val ", symName(tree, name))
          printOpt(": ", tp)
          if (!mods.isDeferred)
            print(" = ", if (rhs.isEmpty) "_" else rhs)

        case dd@DefDef(mods, name, tparams, vparamss, tp, rhs) =>
          //sym info doesn't set after parser
          printAnnotations(tree)
          printModifiers(tree, mods)
          print("def " + symName(tree, name))
          printTypeParams(tparams);
          vparamss foreach printValueParams
          printOpt(": ", tp);
          printOpt(" = ", rhs)

        case TypeDef(mods, name, tparams, rhs) =>
          if (mods hasFlag (PARAM | DEFERRED)) {
            printAnnotations(tree)
            printModifiers(tree, mods);
            //print("!!!")
            print("type ");
            printParam(tree)
          } else {
            printAnnotations(tree)
            printModifiers(tree, mods);
            //print("???")
            print("type " + symName(tree, name))
            printTypeParams(tparams);
            printOpt(" = ", rhs)
          }

        case LabelDef(name, params, rhs) =>
          print(symName(tree, name)); printLabelParams(params); printBlock(rhs)

        case Import(expr, selectors) =>
          // Is this selector remapping a name (i.e, {name1 => name2})
          def isNotRemap(s: ImportSelector): Boolean = (s.name == nme.WILDCARD || s.name == s.rename)
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
          //TODO separate classes and templates
          val currentOwner1 = currentOwner
          if (tree.symbol != NoSymbol) currentOwner = tree.symbol.owner
          //          if (parents exists isReferenceToAnyVal) {
          //            print("AnyVal")
          //          }
          //          else {

          //      val primaryCtr @ DefDef(_, _, _, _, _, Block(List(ap @ Apply(_, ctArgs)), _)) = body collectFirst {
          //        case dd: DefDef => dd
          //      } getOrElse(null)

          val primaryCtr = body collectFirst {
            case dd: DefDef => dd
          } getOrElse (null)

          var ap: Apply = null
          var ctArgs: List[Tree] = null

          primaryCtr match {
            case DefDef(_, _, _, _, _, Block(List(apply@Apply(_, crtArs)), _)) =>
              ap = apply;
              ctArgs = crtArs
            case _ =>
          }

          if (!parents.isEmpty) {
            val (clParent :: traits) = parents
            print(clParent)

//            if (primaryCtr != null && !primaryCtr.isEmpty) {
              //pass parameters to super class constructors
              //get all parameter lists
              val applyParamsList = if (ctArgs != null && !ctArgs.isEmpty){
                ap filter {
                  case apply @ Apply(_, args) => true
                  case _ => false
                } map {
                  apply => val Apply(sel, ars) = apply
                  ars
                } reverse
              } else List(List())
              applyParamsList foreach {x: List[Tree] => if (!x.isEmpty) printRow(x, "(", ", ", ")")}
//            }
            if (!traits.isEmpty) {
              printRow(traits, " with ", " with ", "")
            }
          }
          //remove primary constr def and constr val and var defs
          //right contains all constructors
          val (left, right) = body.filter {
            case vd: ValDef => !vd.mods.hasFlag(PARAMACCESSOR)
            case dd: DefDef => dd.name != nme.MIXIN_CONSTRUCTOR //remove $this$ from traits
            case EmptyTree => false
            case _ => true
          } span {
            case dd: DefDef => dd.name != nme.CONSTRUCTOR
            case _ => true
          }

          val modBody = left ::: right.drop(1)//List().drop(1) ==> List()

          if (!modBody.isEmpty) {
            if (self.name != nme.WILDCARD) {
              print(" { ", self.name);
              printOpt(": ", self.tpt);
              print(" => ")
            } else if (!self.tpt.isEmpty) {
              print(" { _ : ", self.tpt, " => ")
            } else {
              print(" {")
            }
            printColumn(modBody, "", ";", "}")
          }
          //          }
          currentOwner = currentOwner1

        case Block(stats, expr) =>
          printColumn(stats ::: List(expr), "{", ";", "}")

        case Match(selector, cases) =>
          val selectorType1 = selectorType
          selectorType = selector.tpe
          print(selector);
          printColumn(cases, " match {", "", "}")
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
          print(pat);
          printOpt(" if ", guard)
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
          print("(");
          printValueParams(vparams);
          print(" => ", body, ")")
          if (printIds && tree.symbol != null) print("#" + tree.symbol.id)

        case Assign(lhs, rhs) =>
          print(lhs, " = ", rhs)

        case AssignOrNamedArg(lhs, rhs) =>
          print(lhs, " = ", rhs)

        case If(cond, thenp, elsep) =>
          print("if (", cond, ")");
          indent;
          println()
          print(thenp);
          undent
          if (!elsep.isEmpty) {
            println();
            print("else");
            indent;
            println();
            print(elsep);
            undent
          }

        case Return(expr) =>
          print("return ", expr)

        case Try(block, catches, finalizer) =>
          print("try ");
          printBlock(block)
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

//        case Select(apply: Apply, name) if (!settings.debug.value) =>
//          print(apply,".",symName(tree, name))

        case Select(qual@New(tpe), name) if (!settings.debug.value) =>
          print(qual)

        case Select(qualifier, name) =>
          print(backquotedPath(qualifier), ".", symName(tree, name))

        case id@Ident(name) =>
          if (!name.isEmpty) {
            val str = symName(tree, name)
            print(if (id.isBackquoted) "`" + str + "`" else str)
          }
          else {
            print("")
          }

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

          val unchecked = tpt match {
            case Select(_, tname) => tname.toString() == "unchecked"
            case _ => false
          }
          print(tree, if (tree.isType) " " else if (!unchecked) ": " else "")
          if (!unchecked) printAnnot()

        case SingletonTypeTree(ref) =>
          print(ref, ".type")

        case SelectFromTypeTree(qualifier, selector) =>
          print(qualifier, "#", symName(tree, selector))

        case CompoundTypeTree(templ) =>
          print(templ)

        case AppliedTypeTree(tp, args) =>
          //processing of repeated type
          if (tp.exists {
            case Select(_, name) => name == tpnme.REPEATED_PARAM_CLASS_NAME
            case _ => false
          } && !args.isEmpty) {
            print(args(0), "*")
          } else {
            print(tp);
            printRow(args, "[", ", ", "]")
          }

        case TypeBoundsTree(lo, hi) =>
          printOpt(" >: ", lo); printOpt(" <: ", hi)

        case ExistentialTypeTree(tpt, whereClauses) =>
          print("(", tpt);
          printColumn(whereClauses, " forSome { ", ";", "})")

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

    private def symNameInternal(tree: Tree, name: Name, decoded: Boolean): String = {
      val sym = tree.symbol
      if (sym.name.toString == nme.ERROR.toString) {
        "<" + quotedName(name, decoded) + ": error>"
      } else if (sym != null && sym != NoSymbol) {
        val prefix = if (sym.isMixinConstructor) "/*%s*/".format(quotedName(sym.owner.name, decoded)) else ""
        var suffix = ""
        if (settings.uniqid.value) suffix += ("#" + sym.id)
        //if (settings.Yshowsymkinds.value) suffix += ("#" + sym.abbreviatedKindString)
        prefix + quotedName(tree.symbol.decodedName) + suffix
      } else {
        //val str = if (nme.isConstructorName(name)) "this"
        val str = if (name == nme.CONSTRUCTOR) "this"
        else quotedName(name, decoded)
        str
      }
    }

    def decodedSymName(tree: Tree, name: Name) = symNameInternal(tree, name, true)

    def symName(tree: Tree, name: Name) = symNameInternal(tree, name, false)


    override def print(args: Any*): Unit = args foreach {
      case tree: Tree =>
        printPosition(tree)
        printTree(
          if (tree.isDef && tree.symbol != NoSymbol && tree.symbol.isInitialized) {
            tree match {
              case ClassDef(_, _, _, impl@Template(ps, emptyValDef, body))
                if (tree.symbol.thisSym != tree.symbol) =>
                ClassDef(tree.symbol, Template(ps, ValDef(tree.symbol.thisSym), body))
              case ClassDef(_, _, _, impl) => ClassDef(tree.symbol, impl)
              case ModuleDef(_, _, impl) => ModuleDef(tree.symbol, impl)
              case ValDef(_, _, _, rhs) => ValDef(tree.symbol, rhs)
              case DefDef(_, _, _, vparamss, _, rhs) => DefDef(tree.symbol, vparamss, rhs)
              case TypeDef(_, _, _, rhs) => TypeDef(tree.symbol, rhs)
              case _ => tree
            }
          } else tree)
      case unit: CompilationUnit =>
        print("// Scala source: " + unit.source + "\n")
        if (unit.body == null) print("<null>")
        else {
          print(unit.body);
          println()
        }
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