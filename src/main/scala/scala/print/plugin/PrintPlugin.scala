package scala.print.plugin

import scala.tools.nsc
import scala.sprinter.printers._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import scala.tools.nsc.ast.Printers
import java.io.{FileOutputStream, StringWriter, PrintWriter, File}

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

  object afterTyper extends PrintPhaseComponent("typer", "patmat")
  //object afterParser extends PrintPhaseComponent("parser", "namer")

  //val components = List[PluginComponent](afterParser)
  val components = List[PluginComponent](afterTyper)

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

    val defaultDirName = "sourceFromAST"

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

  val defaultDirPath = System.getProperty("user.dir")

  def writeToFile(path: String, body: String, ovrride: Boolean = true) = {
    val file = new File(path)
    val writer = new PrintWriter(new FileOutputStream(file, !ovrride /* append = true */))
//    if (ovrride || !file.exists())
      try {
        writer.write(body)
      } finally {
        writer.close()
      }
  }

  def printImportsAndTypes(printedTypes: Map[String, String], imports: List[Import], fileName: String) = {
    val importsTestFilePath = defaultDirPath + File.separator + ".importsTest"
    val text = new StringBuilder("=======================================\n")
    text.append(fileName + "\n")
    text.append("---------------------------------------\n")
    if (!imports.isEmpty) {
      text.append("imports:\n\n")
      //printing imports
      imports foreach {
        imp => {
          text.append(show(imp) + "\n")
          System.out.println("show(imp): " + show(imp))
          System.out.println("imp.expr: " + imp.expr)
          System.out.println("showRaw(imp): " + showRaw(imp))
          System.out.println("showRaw(imp.expr): " + showRaw(imp.expr))
          imp match {
            case Import(s @ Select(tree, name), _) =>
              System.out.println("import: show(name) = " + showRaw(name))
              System.out.println("import: name = " + name)
              System.out.println("showRaw(s.symbol.name) = " + showRaw(s.symbol.name))
              System.out.println("showRaw(s.symbol) = " + showRaw(s.symbol))
              System.out.println("s.symbol.fullName = " + s.symbol.fullName)
            case Import(ide @ Ident(name), _) =>
              System.out.println("import: show(name) = " + showRaw(name))
              System.out.println("import: name = " + name)
              System.out.println("showRaw(ide.symbol.name) = " + showRaw(ide.symbol.name))
              System.out.println("showRaw(ide.symbol) = " + showRaw(ide.symbol))
              System.out.println("ide.symbol.fullName = " + ide.symbol.fullName)
            case _ =>
          }
        }
      }
      text.append("\n")
      text.append("types:\n\n")
      if (!printedTypes.isEmpty) {
        printedTypes foreach{
          pt => text.append(pt._1 + " |--> " + pt._2 + "\n")
        }
      } else {
        text.append("type map is empty..." + "\n")
      }
    } else {
      text.append("imports are empty..." + "\n")
    }
    //get or create file in current project (defaultDirPath + File.separator + ".importsTest")
    //print file name
    //print imports
    //print each map entry (key -> printType(...)) - if printType is the same - key -> doesn't change
    //all text should be add (don't overwrite existing file)
    text.append("---------------------------------------\n\n")
    writeToFile(importsTestFilePath, text.toString, false)
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
            //regenerate only scala files
            val fileName = unit.source.file.name
            if (fileName.endsWith(".scala")) {
              println("-- Source name: " + fileName + " --")
              val unitTree = unit.body

              val imports = (unitTree.filter{
                case imp:Import => true
                case _ => false
              }).asInstanceOf[List[Import]]
              val typeTrees = (unitTree.filter{
                case ttr: TypTree => true
                case _ => false
              }) distinct


              println("@@@ typeTrees.size: " + typeTrees.size)

              import scala.collection.mutable.{Map => mMap}
              val defaultPrinting = "SAME_T"

              val printedTypes: mMap[String, String] = mMap.empty
              typeTrees foreach {
                fullTree => {
                  val originalString = fullTree.toString()
                  val printedString = printType(fullTree, imports)

                  //System.out.println("}}} originalString: " + originalString)
                  //System.out.println("}}} printedString: " + printedString)
                  if (!printedTypes.contains(originalString))
                    printedTypes += (if (originalString != printedString) (originalString -> printedString) else (originalString -> defaultPrinting))
                  else {
                    val oldValue = (printedTypes get (originalString) getOrElse(""))
                    if (oldValue != printedString && oldValue != defaultPrinting)
                      printedTypes += (originalString + "(1)" -> printedString)
                  }
                  //check if entry in map is already exist and add new entry it new entry value differs from old or if we have new key
                }
              }

              println("@@@ printedTypes.size: " + printedTypes.size)

              //TODO print imports, map and file name (in single file - ++ toFile)
              printImportsAndTypes(printedTypes.toMap, imports, fileName)
            } else
              println("-- Source name: " + fileName + " is not processed")
        } catch {
          case e: Exception =>
            e.printStackTrace()
            throw e
        }
      }
    }

    def printType(what: Tree, imports: List[Import]) =
      printers.showType(what, imports)

    def reconstructTree(what: Tree) = {
      printers.show(what)
    }
  }
}

