import sbt._
import Keys._
import Configurations.CompilerPlugin
import sbtassembly.Plugin._
import AssemblyKeys._
import com.typesafe.sbt.SbtSite.site
import com.typesafe.sbt.SbtSite.SiteKeys._
import com.typesafe.sbt.site.JekyllSupport.Jekyll
import com.typesafe.sbt.SbtGhPages.ghpages
import com.typesafe.sbt.SbtGit.git

object SourcePrinter extends Build {
  val printPluginName = "printplugin"
  val jarsToExclude = Seq("scala-reflect.jar", "scala-library.jar", "scala-compiler.jar")
  val buildSettings = Defaults.defaultSettings ++
    Seq(
      organization := "org.scala-lang.plugins",
      version := "0.2.0",
      scalaVersion := "2.11.0-M7"
    )
    val assemblyProjectSettings = Seq(
      excludedJars in assembly <<= (fullClasspath in assembly) map { cp => 
        cp filter {cpj => jarsToExclude.contains(cpj.data.getName)}
      }
    ) 

    val printPlugin = Project(printPluginName, file("."),
      settings = buildSettings ++ assemblySettings ++ assemblyProjectSettings ++ websiteSettings ++
      addArtifact(Artifact(printPluginName, "assembly"), sbtassembly.Plugin.AssemblyKeys.assembly)) settings (
	     name := printPluginName,

      //crossVersion := CrossVersion.full,
      //exportJars := true,
      libraryDependencies <++= scalaVersion apply dependencies
    ) 

    lazy val websiteSettings: Seq[Setting[_]] = (
     site.settings ++
     ghpages.settings ++
     site.includeScaladoc() ++
     site.jekyllSupport() ++
     Seq(
       git.remoteRepo := "https://github.com/VladimirNik/printPlugin.git",
       includeFilter in Jekyll := ("*.html" | "*.png" | "*.js" | "*.css" | "CNAME")
     )
   )

    def dependencies(sv: String) = Seq(
      "org.scala-lang" % "scala-compiler" % sv,
      "org.scala-lang" % "sprinter-tree_2.11.0-M7" % "0.2.0"
    )
}
