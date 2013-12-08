import sbt._
import Keys._
import Configurations.CompilerPlugin

object SourcePrinter extends Build {
  val printPluginName = "printplugin"
  val buildSettings = Defaults.defaultSettings ++
    Seq(
      organization := "org.scala-lang.plugins",
      version := "0.2.0",
      //autoScalaLibrary := false,
      //scalaVersion := "2.11.0-local",
      scalaHome := Some(file("/home/vova/scala-projects/GSoC/scala/scala/build/pack")),
      scalaVersion := "2.11.0-M7", 
      libraryDependencies <++= scalaVersion apply dependencies
    ) 

    val printPlugin = Project(printPluginName, file("."),
      settings = buildSettings) settings (name := printPluginName) 
    
    def dependencies(sv: String) = Seq(
      "org.scala-lang" % "scala-compiler" % sv
    )
}
