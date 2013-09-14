package scala.print.plugin

import scala.tools.nsc
import scala.pretty.printers._
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import java.io.{StringWriter, PrintWriter, File}

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
      if (unit.source.file != null && unit.source.file.file.getParentFile != null) { //TODO remove null
        val currentFilePath = unit.source.file.file.getParentFile.getAbsolutePath
        val genSourcePath = if (overrideSrc) currentFilePath
          else currentFilePath.replaceFirst(defaultDirPath, defaultDirPath + File.separator + dirName + File.separator + folderName).replaceFirst(defaultDirPath, baseDir)
        val dir = new File(genSourcePath)
        dir.mkdirs()

        val filePath = genSourcePath + File.separator + unit.source.file.name
        writeToFile(filePath, sourceCode)

        //check file creation
        val checkFilePath = defaultDirPath + File.separator + ".checkSrcRegen"
        writeToFile(checkFilePath, "source regeneration: " + new java.util.Date(), false)
      } else {
        println("Can't process unit: " + unit)
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

    val printers = new PrettyPrinters(global)

    val phaseName = "printSourceAfter_" + prevPhase
    def newPhase(_prev: Phase): StdPhase = new PrintPhase(_prev)

    class PrintPhase(prev: Phase) extends StdPhase(prev) {
      override def name = PrintPlugin.this.name

      def apply(unit: CompilationUnit) {
        try {
            //regenerate only scala files
            //val printers = new PrettyPrinters(global)
            val sourceCode = if (unit.source.file.name.contains(".scala")) reconstructTree(unit.body)
              else unit.source.content.mkString
            println("-- Source name: " + unit.source.file.name + " --")
            writeSourceCode(unit, sourceCode, "before_" + nextPhase)
        } catch {
          case e: Exception =>
            e.printStackTrace()
            throw e
        }
      }
    }

    def reconstructTree(what: Tree) = {
      printers.show(what)
    }
  }
}

