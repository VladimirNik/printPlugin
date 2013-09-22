printPlugin
============

Scala compiler plugin:

- use scala-pretty-printer library
- generate project sources based on program's ASTs

### Build process

from printplugin directory run:

```
sbt assembly
```

Produced jar should be in project's target directory.

see <http://scala-sbt.org/release/docs/Getting-Started/Setup.html> to setup sbt.

### Usage

For sbt project:

In the target project add to build.sbt (or build.scala) following option:

```
scalacOptions += "-Xplugin:/path/to/printPlugin/target/scala-2.10/printplugin-assembly-0.2.0.jar"
```

Compile the project: 

```
sbt compile
```

After the compilation generated sources should be in sourceFromAST folder (projectFolder/sourceFromAST).

Command-line projects:

To compile the project from the command-line use:

```
scalac -Xplugin:/path/to/jar/printplugin-assembly-0.2.0.jar hello/world/*.scala
```

### Options

dir-name - setup custom name for folder with regenerated sources

```
scalacOptions += "-P:printplugin:dir-name:printAST"
```

base-dir - setup custom path for regenerated sources 

```
scalacOptions += "-P:printplugin:base-dir:[/some/path]"
```

oversrc - overwrite original sources with generated during the compilation.

```
scalacOptions += "-P:printplugin:oversrc"
```

### Example

```
scalac -Xplugin:path/to/jar/printplugin_2.10-assembly.jar -P:printplugin:base-dir:/path/to/generated/sources -P:printplugin:dir-name:source-hello-world hello/world/*.scala
```

Regenerated sources should be in sourceFromAST folder .

sbt configuration can be found in treePrintTester project - <https://github.com/VladimirNik/treePrintTester>
