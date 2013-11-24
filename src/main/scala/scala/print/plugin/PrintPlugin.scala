package scala.print.plugin

import scala.tools.nsc
import scala.sprinter.printers._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import scala.tools.nsc.ast.Printers
import java.io.{StringWriter, PrintWriter, File}

import scala.tools.refactoring.Refactoring
import scala.tools.refactoring.common.CompilerAccess
import scala.reflect.io.AbstractFile
import scala.tools.refactoring.sourcegen.ReusingPrinter
import scala.reflect.internal.MissingRequirementError
import scala.reflect.internal.util.BatchSourceFile

object PrintPlugin {
  val baseDirectoryOpt = "base-dir:"
  val dirNameOpt = "dir-name:"
  val overSrcOpt = "oversrc"
}

class PrintPlugin(val global: Global) extends Plugin {
  import PrintPlugin._
  import global._

  val name = "printplugin"
  val description = "print source code from AST after parser phase"

  var baseDir: String = System.getProperty("user.dir")
  var dirName = "sourceFromAST"
  var overrideSrc = false

  //object afterTyper extends PrintPhaseComponent("typer", "patmat")
  object afterParser extends PrintPhaseComponent("parser", "namer")

  val components = List[PluginComponent](afterParser)
  //val components = List[PluginComponent](afterTyper)

  override def processOptions(options: List[String], error: String => Unit) {
    for (option <- options) {
      if (option.startsWith(baseDirectoryOpt)) {
        baseDir = option.substring(baseDirectoryOpt.length)
      } else if (option.startsWith(dirNameOpt)) {
        dirName = option.substring(dirNameOpt.length)
      } else if (option.endsWith(overSrcOpt)) {
        overrideSrc = true
      } else{
          error("Option not understood: "+option)
      }
    }
  }

  def writeSourceCode(unit: CompilationUnit, sourceCode: String, folderName: String) {

    def writeToFile(path: String, body: String, ovrride: Boolean = true) = {
      val file = new File(path)
        if (ovrride || !file.exists()) {
        val writer = new PrintWriter(file)
          try {
          writer.write(body)
        } finally {
          writer.close()
        }
      }
    }

    val defaultDirName = "sourceFromAST"
    val defaultDirPath = System.getProperty("user.dir")

    try {
      def findActualPath(filePath: String) =
        if (overrideSrc) filePath
          else filePath.replaceFirst(defaultDirPath, defaultDirPath + File.separator + dirName + File.separator + folderName)
            .replaceFirst(defaultDirPath, baseDir)

      val currentFile = unit.source.file.file
      Option(currentFile) map {
        currentFile =>
          val currentFilePath = currentFile.getAbsolutePath
          val actualPath = findActualPath(currentFilePath)
          println("currentFilePath: " + currentFilePath)
          println("actualPath: " + actualPath)

          //if parent file exists - create all dirs
          //currentFile.getParentFile.exists
          for (parentFile <- Option(currentFile.getParentFile)) {
            val parentFilePath = parentFile.getAbsolutePath
            val actualParentPath = findActualPath(parentFilePath)

            println("parentFilePath: " + parentFilePath)
            println("actualParentPath: " + actualParentPath)

            val dir = new File(actualParentPath)
            dir.mkdirs()
          }

          writeToFile(actualPath, sourceCode)

          //check file creation
          val checkFilePath = defaultDirPath + File.separator + ".checkSrcRegen"
          writeToFile(checkFilePath, "source regeneration: " + new java.util.Date(), false)
      } getOrElse {
        println("Can't process unit: " + unit)
//        for (ufile <- Option(unit.source.file)) {
//          for (ufilefile <- Option(ufile.file)) {
//            println("absolute path: " + ufilefile.getAbsolutePath)
//            for (ufilefileparent <- Option(ufilefile.getParentFile)) {
//              println("parent absolute path: " + ufilefileparent.getAbsolutePath)
//            }
//          }
//        }
      }
    } catch {
      case e @ _ => println("Error during processing unit: " + unit)
      throw e
    }
  }

  //Phase should be inserted between prevPhase and nextPhase
  //but it possible that not right after prevPhase or not right before nextPhase
  class PrintPhaseComponent(val prevPhase: String, val nextPhase: String) extends PluginComponent {
    val global: PrintPlugin.this.global.type = PrintPlugin.this.global

    override val runsAfter = List[String](prevPhase)
    override val runsBefore = List[String](nextPhase)

    val printers = PrettyPrinters(global)

    val phaseName = "printSourceAfter_" + prevPhase
    def newPhase(_prev: Phase): StdPhase = new PrintPhase(_prev)

    class PrintPhase(prev: Phase) extends StdPhase(prev) {
      override def name = PrintPlugin.this.name

      def apply(unit: CompilationUnit) {
        try {
          val compiler = global
            //regenerate only scala files
            val fileName = unit.source.file.name
            if (fileName.endsWith(".scala")) {
              println("-- Source name: " + fileName + " --")

              val tree: Tree = (unit.body.find{
                tr => tr.isInstanceOf[PackageDef]
              }).get

              System.out.println("tree: " + tree)

              val sourceCode = generateSource(unit.body, unit)
              //val sourceCode = TestRef.createText(tree.asInstanceOf[TestRef.global.Tree])
              writeSourceCode(unit, sourceCode, "before_" + nextPhase)
              System.out.println("Resulted code:\n" + sourceCode)
            } else
              println("-- Source name: " + fileName + " is not processed")
        } catch {
          case e: Exception =>
            e.printStackTrace()
            throw e
        }
      }
    }

    def generateSource(what: Tree, unit: CompilationUnit) = {
      def getCompiler = global
//      def getCompiler = {
//        import scala.tools.nsc._
//        import scala.tools.nsc.reporters._
//        val settings = new Settings()
//
//        val COLON = System getProperty "path.separator"
//
//        settings.classpath.value = this.getClass.getClassLoader match {
//          case ctx: java.net.URLClassLoader => ctx.getURLs.map(_.getPath).mkString(COLON)
//          case _                            => System.getProperty("java.class.path")
//        }
//
//        settings.bootclasspath.value = Predef.getClass.getClassLoader match {
//          case ctx: java.net.URLClassLoader => ctx.getURLs.map(_.getPath).mkString(COLON)
//          case _                            => System.getProperty("sun.boot.class.path")
//        }
//
//        settings.encoding.value = "UTF-8"
//        settings.outdir.value = "."
//        settings.extdirs.value = ""
//
//        val reporter = new ConsoleReporter(settings, null, new PrintWriter(System.out)) //writer
//        new scala.tools.nsc.Global(settings, reporter)
//      }

      def getInteractiveCompiler(global: nsc.Global) = {
        val comp = new nsc.interactive.Global(global.settings, global.reporter)
        try {
          comp.ask { () =>
            new comp.Run
          }
        } catch {
          case e: MissingRequirementError =>
            val msg = s"""Could not initialize the compiler!""".stripMargin
            throw new Exception(msg, e)
        }
        comp
      }

      trait GlobalRefCompiler extends Refactoring with CompilerAccess {
        val global: nsc.Global
        import global._

        def compilationUnitOfFile(f: AbstractFile): Option[global.CompilationUnit] = Option(unit.asInstanceOf[global.CompilationUnit])

        def generatePrint(tree: Tree, changeset: ChangeSet = AllTreesHaveChanged, sourceFile: Option[scala.reflect.internal.util.SourceFile]): String = {
          val initialIndentation = if(tree.hasExistingCode) indentationString(tree) else ""
          val in = new Indentation(defaultIndentationStep, initialIndentation)

          print(tree, PrintingContext(in, changeset, tree, sourceFile)).asText
        }

        def print(tree: global.Tree): String = {
          generatePrint(tree, sourceFile = None)
        }
      }

      val demoSourceStr = """
      case class Test {
        import scala.collection.mutable
        val x: mutable.Map[Int, Int] = null
      }
"""

      trait InterRefCompiler extends GlobalRefCompiler {
        val global: nsc.interactive.Global

        def cleanTree(t: global.Tree) = {
          global.ask{ () =>
            val removeAuxiliaryTrees = ↓(transform {
              case t: global.Tree if (t.pos == global.NoPosition || t.pos.isRange) => t
              case t: global.ValDef => global.emptyValDef
              // We want to exclude "extends AnyRef" in the pretty printer tests
              case t: global.Select if t.name.isTypeName && t.name.toString != "AnyRef" => t
              case t => global.EmptyTree
            })

            (removeAuxiliaryTrees &> topdown(setNoPosition))(t).get
          }
        }

        def shutdown() =
          global.askShutdown()

        override def print(tree: global.Tree): String = {
          val initialIndentation = if(tree.hasExistingCode) indentationString(tree) else ""
          val in = new Indentation(defaultIndentationStep, initialIndentation)

          val res = generatePrint(cleanTree(tree), sourceFile = None)
          //val res = generatePrint(tree, sourceFile = None)
          res
        }
      }

      object GlobalRefInstance extends GlobalRefCompiler {
        val global = getCompiler
      }

      object InferRefInstance extends InterRefCompiler {
        val global: nsc.interactive.Global = getInteractiveCompiler(getCompiler)

//        val file = new BatchSourceFile("fileName", demoSourceStr)
        val testTree = global.parseTree(unit.source)
      }

//      System.out.println("what.pos: " + what.pos)
//      System.out.println("what.pos.isDefined: " + InferRefInstance.cleanTree(what.asInstanceOf[InferRefInstance.global.Tree]).pos.isDefined)
//      System.out.println("what.pos.isOpaqueRange: " + InferRefInstance.cleanTree(what.asInstanceOf[InferRefInstance.global.Tree]).pos.isOpaqueRange)
//      System.out.println("what.pos.isRange: " + InferRefInstance.cleanTree(what.asInstanceOf[InferRefInstance.global.Tree]).pos.isRange)
//      System.out.println("what.pos.isTransparent: " + InferRefInstance.cleanTree(what.asInstanceOf[InferRefInstance.global.Tree]).pos.isTransparent)
//
//      System.out.println("InferRefInstance.testTree.pos: " + InferRefInstance.testTree.pos)
//      System.out.println("InferRefInstance.testTree.isDefined: " + InferRefInstance.testTree.pos.isDefined)
//      System.out.println("InferRefInstance.testTree.pos.isOpaqueRange: " + InferRefInstance.testTree.pos.isOpaqueRange)
//      System.out.println("InferRefInstance.testTree.pos.isRange: " + InferRefInstance.testTree.pos.isRange)
//      System.out.println("InferRefInstance.testTree.pos.isTransparent: " + InferRefInstance.testTree.pos.isTransparent)

      val res = InferRefInstance.print(InferRefInstance.testTree.asInstanceOf[InferRefInstance.global.Tree])
//      val res = GlobalRefInstance.print(InferRefInstance.testTree.asInstanceOf[GlobalRefInstance.global.Tree])
      InferRefInstance.shutdown()
      res
//      printers.show(InferRefInstance.testTree, PrettyPrinters.AFTER_TYPER, printMultiline = true, decodeNames = true)
    }
  }
}