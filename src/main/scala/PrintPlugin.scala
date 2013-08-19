package com.vladimir.nik.print.plugin

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import java.io.{StringWriter, PrintWriter, File}
import com.vladimir.nik.print.plugin.printer.ASTPrinters

object PrintPlugin {
  val baseDirectoryOpt = "base-dir:"
  val dirNameOpt = "dir-name:"
  val overSrcOpt = "oversrc"
  //val projectDirOpt = "project-dir:"
}

class PrintPlugin(val global: Global) extends Plugin {
  import PrintPlugin._
  import global._

  val name = "printplugin"
  val description = "print source code from AST after patmat and parser phases"

  var baseDir: String = System.getProperty("user.dir")
  var dirName = "sourceFromAST"
  var overrideSrc = false
  //var projectDir: String = ""

//  object afterParser extends PrintPhaseComponent("parser", "namer") {
//
//    override def newPhase(_prev: Phase) = new PrintPhase(_prev)
//
//    //TODO: refactor
//    class PrintPhase(prev: Phase) extends StdPhase(prev) {
//      override def name = PrintPlugin.this.name
//      def apply(unit: CompilationUnit) {
//        try {
//          //writeSourceCode(unit, unit.source.content.mkString, "originalSource")
//          val sourceCode = show(unit.body)
//          writeSourceCode(unit, sourceCode, "before_" + nextPhase)
//          println(sourceCode)
//        } catch {
//          case e: Exception =>
//            e.printStackTrace()
//        }
//      }
//    }
//  }

  object afterTyper extends PrintPhaseComponent("typer", "patmat")
  object afterParser extends PrintPhaseComponent("parser", "namer")

  val components = List[PluginComponent](afterParser)

  override def processOptions(options: List[String], error: String => Unit) {
    for (option <- options) {
      if (option.startsWith(baseDirectoryOpt)) {
        baseDir = option.substring(baseDirectoryOpt.length)
      } else if (option.startsWith(dirNameOpt)) {
        dirName = option.substring(dirNameOpt.length)
      //} else if (option.startsWith(projectDirOpt)) {
      //  projectDir = option.substring(projectDirOpt.length)
      } else if (option.endsWith(overSrcOpt)) {
        overrideSrc = true
      } else{
          error("Option not understood: "+option)
      }
    }
  }

  def writeSourceCode(unit: CompilationUnit, sourceCode: String, folderName: String) {
    this.synchronized{
      //generate name for default folder
      val defaultDirName = "sourceFromAST"

      //default dir path
      //val defaultDir = "."
      val defaultDir = System.getProperty("user.dir")
      //System.out.println("defaultDir: " + defaultDir)
      //val sbtSourcePath = "src/main/scala"

      val currentFilePath = unit.source.file.file.getParentFile.getAbsolutePath
      System.out.println(" === getting path info: ===")
      System.out.println("currentFilePath: " + currentFilePath)
      val genSourcePath = if (overrideSrc) currentFilePath
        else currentFilePath.replaceFirst(defaultDir, defaultDir + File.separator + dirName + File.separator + folderName).replaceFirst(defaultDir, baseDir)
      //System.out.println("genSourcePath: " + genSourcePath)
      System.out.println("genSourcePath: " + genSourcePath)
      val dir = new File(genSourcePath)
      dir.mkdirs()

      val filePath = genSourcePath + File.separator + unit.source.file.name
      System.out.println("filePath: " + filePath)
      val writer = new PrintWriter(new File(filePath))
      try {
        writer.write(sourceCode)
      } finally {
      writer.close()
      }
    }
  }

  //Phase should be inserted between prevPhase and nextPhase
  //but it possible that not right after prevPhase or not right before nextPhase
  class PrintPhaseComponent(val prevPhase: String, val nextPhase: String) extends PluginComponent {
    val global: PrintPlugin.this.global.type = PrintPlugin.this.global

    override val runsAfter = List[String](prevPhase)
    //override val runsRightAfter = Option(prevPhase)
    override val runsBefore = List[String](nextPhase)

    val phaseName = "printSourceAfter_" + prevPhase
    def newPhase(_prev: Phase): StdPhase = new PrintPhase(_prev)

    class PrintPhase(prev: Phase) extends StdPhase(prev) {
      override def name = PrintPlugin.this.name

      def apply(unit: CompilationUnit) {
        try {
          val sourceCode = if (!unit.source.file.name.contains(".java")) show(unit.body)
            else unit.source.content.mkString
          writeSourceCode(unit, sourceCode, "before_" + nextPhase)
          //println("showRaw(unit.body): " + showRaw(unit.body))
          println(sourceCode)
        } catch {
          case e: Exception =>
            e.printStackTrace()
            throw e
        }
      }
    }

    def show(what: Any) = {

      val buffer = new StringWriter()
      val writer = new PrintWriter(buffer)

      val printers = new ASTPrinters(global, writer)
      var printer = new printers.ASTPrinter


      printer.print(what)
      writer.flush()
      buffer.toString
    }
  }
}

