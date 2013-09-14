import sbt._
import Keys._
import Configurations.CompilerPlugin

object SourcePrinter extends Build {
   val printPlugin = Project("printPlugin", file(".")) settings (
	name := "printPlugin",
	organization := "org.scala-lang.plugins",
        //organization := "test.org",
	//version := "1.0",
        version := "0.2.0",
	scalaVersion := "2.10.2",
        //crossVersion := CrossVersion.full,
        //exportJars := true,
	libraryDependencies <++= scalaVersion apply dependencies
   )

   def dependencies(sv: String) = Seq(
      "org.scala-lang" % "scala-compiler" % sv,
      "org.scala-lang" %% "scala-pretty-printer" % "0.2.0")
}
