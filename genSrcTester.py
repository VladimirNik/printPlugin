import fileinput
import sys
from subprocess import call
import re
import os
import tempfile

#pattern to find project's name
nameRe = re.compile(".*name:\s*\"(.+)\".*")
uriRe = re.compile(".*uri:\s*\"(.+)#(.*)\".*")
printDeps1 = re.compile(".*\"set\s*scalacOptions.*\"")
printDeps2 = re.compile(".*\"set\s*libraryDependencies.*\"")
printDeps3 = re.compile(".*\"set\s*publishArtifact.*\"")
printDeps4 = re.compile(".*\"set\s*addCompilerPlugin.*\"")

#individual settings

dbuildPath = '/home/vova/scala-projects/GSoC/script2/dbuild-0.6.4/'
dbuildGenSourcePath = os.getcwd()

separator = "/"
dbuildExec = dbuildPath + 'bin' + separator + 'dbuild'
dbuildProjects = dbuildGenSourcePath + separator + 'target-0.6.4' + separator + 'project-builds' + separator
printPlugin = 'printPlugin'

filename = sys.argv[-1]

print("dbuildExec: " + dbuildExec)
print("filename: " + filename)
#uncomment this
call([dbuildExec, filename])

names = []
uriNames = []
prKeys = {}
prFiles = {}
prBranches = {}

text=open(sys.argv[1], "r").read().split('\n')
currentName = ""
for line in text:
    match = re.match(nameRe, line)
    uriMatch = re.match(uriRe, line)
    if match:
        prName = match.group(1)
        currentName = prName
        if prName != printPlugin:
    	    names.append(prName.strip())
    if uriMatch:
        prBranch = uriMatch.group(2)
        if prBranch:
           prBranches[currentName] = prBranch
        else:
           prBranches[currentName] = 'master'
files = os.popen('ls -tr ' + dbuildProjects).read().split('\n')

print('prBranches:')
print(prBranches)

for prName in names:
    for fileName in reversed(files):
        if prName.lower() in fileName.lower():
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
       commandCheckDbuild = "ls " + dbuildProjects + prKeys.get(prName, prName) + separator + ".checkSrcRegen"
       commandSbt = "(cd " + dbuildProjects + prKeys.get(prName, prName) + " && git checkout " + prBranches.get(prName, 'master') + " && sbt compile)"
       print(commandSbt)
       #checkOutput = 0
       #commandOutput = 0
       checkOutput = os.system(commandCheckDbuild)
       if checkOutput == 0:
          commandOutput = os.system(commandSbt)
          if commandOutput != 0:
             errorsSbt = errorsSbt + 1
          prCodes[prName] = commandOutput
       else:
          prCodes[prName] = -1

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
       print("")

if errorsDbuild==1:
    print(str(errorsDbuild) + " project has errors during source regeneration (dbuild).")
elif errorsDbuild>0:
    print(str(errorsSbt) + " projects have errors during source regeneration (dbuild).")
else:
    print("All sources are successfully regenerated (dbuild).")

if errorsSbt==1:
    print(str(errorsSbt) + " project has errors during compilation (sbt).")
elif errorsSbt>0:
    print(str(errorsSbt) + " projects have errors during compilation (sbt).")
else:
    print("All projects compiled successfully (sbt).")
