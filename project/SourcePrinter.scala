import sbt._
import Keys._
import Configurations.CompilerPlugin
import sbtassembly.Plugin._
import AssemblyKeys._

object SourcePrinter extends Build {
  val printPluginName = "printplugin"
  val jarsToExclude = Seq("scala-reflect.jar", "scala-library.jar", "scala-compiler.jar")
  val buildSettings = Defaults.defaultSettings ++
    Seq(
      organization := "org.scala-lang.plugins",
      version := "0.2.0",
   	  scalaVersion := "2.10.2"
    )
    val assemblyProjectSettings = Seq(
      excludedJars in assembly <<= (fullClasspath in assembly) map { cp => 
        cp filter {cpj => jarsToExclude.contains(cpj.data.getName)}
      }
    ) 

    val printPlugin = Project(printPluginName, file("."),
      settings = buildSettings ++ assemblySettings ++ assemblyProjectSettings ++ 
      addArtifact(Artifact(printPluginName, "assembly"), sbtassembly.Plugin.AssemblyKeys.assembly)) settings (
	     name := printPluginName,

      //crossVersion := CrossVersion.full,
      //exportJars := true,
      libraryDependencies <++= scalaVersion apply dependencies
    )

    def dependencies(sv: String) = Seq(
      "org.scala-lang" % "scala-compiler" % sv,
      "org.scala-lang" %% "scala-pretty-printer" % "0.2.0"
    )
}
