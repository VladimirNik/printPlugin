import fileinput
import sys
from subprocess import call
import re
import os
import tempfile

#pattern to find project's name
nameRe = re.compile(".*name:\s*\"(.+)\".*")
uriRe = re.compile(".*uri:\s*\"(.+)#(.*)\".*")
#for last project in the file if version is not set up explicitly we set up dbuild default sbt version (if any)
sbtRe = re.compile(".*sbt-version.*:.*\"(.*)\".*")
fileRe = re.compile("(.*)-.*")
printDeps1 = re.compile(".*\"set\s*scalacOptions.*\"")
printDeps2 = re.compile(".*\"set\s*libraryDependencies.*\"")
printDeps3 = re.compile(".*\"set\s*publishArtifact.*\"")
printDeps4 = re.compile(".*\"set\s*addCompilerPlugin.*\"")

#individual settings

dbuildPath = '/home/vova/scala-projects/GSoC/script2/dbuild-0.6.4/'
dbuildGenSourcePath = os.getcwd()
sbtDefaultVersion = '0.13.0-RC4'

separator = "/"
dbuildExec = dbuildPath + 'bin' + separator + 'dbuild'
dbuildProjects = dbuildGenSourcePath + separator + 'target-0.6.4' + separator + 'project-builds' + separator
printPlugin = 'printPlugin'

filename = sys.argv[-1]

print("dbuildExec: " + dbuildExec)
print("filename: " + filename)
#uncomment this
call([dbuildExec, filename])
#callVal = os.system(dbuildExec + " " + fileName)

names = []
uriNames = []
prKeys = {}
prFiles = {}
prBranches = {}
prSbts = {}

text=open(sys.argv[1], "r").read().split('\n')
currentName = ""
setSbtForCurrentProject = False
for line in text:
    match = re.match(nameRe, line)
    uriMatch = re.match(uriRe, line)
    sbtMatch = re.match(sbtRe, line)
    if match:
        prName = match.group(1)
        currentName = prName
        setSbtForCurrentProject = True
        if prName != printPlugin:
    	    names.append(prName.strip())
    if uriMatch:
        prBranch = uriMatch.group(2)
        if prBranch:
           prBranches[currentName] = prBranch
        else:
           prBranches[currentName] = 'master'
    if sbtMatch:
        print('sbt match...')
  	prSbt = sbtMatch.group(1)
        print('prSbt = ' + prSbt)
        print('setSbtForCurrentProject: ' + str(setSbtForCurrentProject))
	if prSbt and setSbtForCurrentProject:
	   prSbts[currentName] = prSbt
           print("currentName = " + currentName)
           setSbtForCurrentProject = False
           print("-------------------------")
files = os.popen('ls -tr ' + dbuildProjects).read().split('\n')

print('prBranches:')
print(prBranches)
print('')
print('prSbts:')
print(prSbts)

for prName in names:
    for fileName in reversed(files):
        fileMatch = re.match(fileRe, fileName)
        if fileMatch:
           prPart = fileMatch.group(1)
           print("prPart = " + prPart)
	   if prPart.strip and (prName.lower() == prPart.lower()):
              prKeys[prName] = fileName
              break
print("prKeys: ")
print(prKeys)

prCodes = {}

print("")
print("==============================================")
print("Compiling projects with regenerated sources...")
print("==============================================")
print("")

errorsDbuild = 0
errorsSbt = 0

for prName in names:
   if not (printPlugin.lower() in prName.lower()):
       #(cd myPath/ && exec sbt "run arg1")
       print("----------------------------")
       print("   Processing " + prName)
       print("----------------------------")
       if prSbts.get(prName, ''):
          try:
             # This will create a new file or overwrite existing file
             # After dbuild compilation there is no build.properties file
             # We need to regenerate it
             f = open(dbuildProjects + prKeys.get(prName, prName) + separator + "project" + separator + "build.properties", "w")

             try:
                f.write('sbt.version='+prSbts.get(prName, sbtDefaultVersion))
             finally:
                f.close()

          except IOError:
             print("ERROR: build.properties generation failed") 

       commandCheckDbuild = "ls " + dbuildProjects + prKeys.get(prName, prName) + separator + ".checkSrcRegen"
       commandSbt = "(cd " + dbuildProjects + prKeys.get(prName, prName) + " && git checkout " + prBranches.get(prName, 'master') + " && sbt clean && sbt compile && sbt clean && sbt test)"
       print(commandSbt)

       #checkOutput = 0
       #commandOutput = 0
       checkOutput = os.system(commandCheckDbuild)
       if checkOutput == 0:
          commandOutput = os.system(commandSbt)
          if commandOutput != 0:
             errorsSbt += 1
          prCodes[prName] = commandOutput
       else:
          errorsDbuild += 1
          prCodes[prName] = -1

       #remove all generated build.properties to correctly run dbuild next time
       if prSbts.get(prName, ''):
          commandRm = "rm " + dbuildProjects + prKeys.get(prName, prName) + separator + "project" + separator + "build.properties"
          print(commandRm)
          os.system(commandRm)

print("")
print("==============================================")
print("                  Results:                    ")
print("==============================================")
print(prCodes)
print("")
for prName in names:
    if not (printPlugin.lower() in prName.lower()):
       #print("Project: " + prName + ", prCode: " + str(prCodes.get(prName)))
       if (prCodes.get(prName) == 0):
          print("Project: " + prName + " ===> compilation is successful <===")
       elif (prCodes.get(prName) == -1):
          print("Project: " + prName + " ===> code regeneration failed (dbuild) <===")
       else:
          print("Project: " + prName + " ===> compilation failed (sbt) <===")
       print("Branch: " + prBranches.get(prName, 'master'))
       print("Path: " + dbuildProjects + prKeys.get(prName, prName))
       print("Sbt-version: " + prSbts.get(prName, 'system-default'))
       print("")

if errorsDbuild==1:
    print(str(errorsDbuild) + " project has errors during source regeneration (dbuild).")
elif errorsDbuild>0:
    print(str(errorsDbuild) + " projects have errors during source regeneration (dbuild).")
else:
    print("All sources are successfully regenerated (dbuild).")

if errorsSbt==1:
    print(str(errorsSbt) + " project has errors during compilation (sbt).")
elif errorsSbt>0:
    print(str(errorsSbt) + " projects have errors during compilation (sbt).")
elif errorsDbuild == 0:
    print("All projects compiled successfully (sbt).")
