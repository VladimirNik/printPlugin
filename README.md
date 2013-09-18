printPlugin
============

Scala compiler plugin 

- use scala-pretty-printer library
- generate project sources based on program's ASTs

### Build process

go to printplugin directory and run:

```
sbt assembly
```

Produced jar should have the following path:
path/to/ivy2/local/repo/org.scala-lang.plugins/printplugin_2.10/0.2.0/jars/printplugin_2.10-assembly.jar

see <http://scala-sbt.org/release/docs/Getting-Started/Setup.html> to setup sbt.

### Usage

For sbt project:

In the target project add to build.sbt (or build.scala) following option:

```
scalacOptions += "-Xplugin:[path_to_printPlugin]/target/scala-2.10/printplugin_2.10-1.0.jar"
```

Compile the project: 

```
sbt assembly
```

After compilation generated source should be in sourceFromAST folder (projectFolder/sourceFromAST).

Command-line projects:

To compile project in the command-line use:

```
scalac -Xplugin:/path/to/jar/printplugin_2.10-assembly.jar hello/world/*.scala
```

Current version limitation: sources from default package are not regenerated (if compile project directly using scalac).

### Options

dir-name - setup custom name for folder with regenerated sources

```
scalacOptions += "-P:printplugin:dir-name:printAST"
```

base-dir - setup custom path for regenerated sources 

```
scalacOptions += "-P:printplugin:base-dir:[/some/path]"
```

oversrc - overwrite original project's sources with generated during compilation.

```
scalacOptions += "-P:printplugin:oversrc"
```

### Example

```
scalac -Xplugin:path/to/jar/printplugin_2.10-assembly.jar -P:printplugin:base-dir:/path/to/generated/sources -P:printplugin:dir-name:source-hello-world hello/world/*.scala
```

Regenerated sources should be in sourceFromAST folder .

sbt configuration can be found in treePrintTester project - <https://github.com/VladimirNik/treePrintTester>
