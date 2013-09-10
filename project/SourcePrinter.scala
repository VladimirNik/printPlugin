	import sbt._
	import Keys._
	import Configurations.CompilerPlugin

object SourcePrinter extends Build
{
	lazy val main = Project("printPlugin", file(".")) settings(
		name := "printPlugin",
		organization in ThisBuild := "test.org",
		version in ThisBuild := "0.1.0",
		scalaVersion in ThisBuild := "2.10.2",
		exportJars := true,
		libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ % "provided")
	)
}
