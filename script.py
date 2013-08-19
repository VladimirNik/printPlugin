import fileinput
import sys
from subprocess import call
import re
import os
import tempfile

#pattern to find project's name
nameRe = re.compile(".*name:\s*\"(.+)\".*")
uriRe = re.compile(".*uri:\s*\"(.+)\".*")

#individual settings
dbuildPath = '/home/vova/programs/dbuild/dbuild-0.6.3/'

dbuildExec = dbuildPath + '/bin/dbuild'
dbuildProjects = dbuildPath + 'target-0.6.3/project-builds/'
printPlugin = 'printPlugin'

filename = sys.argv[-1]

#uncomment this
call([dbuildExec, filename])

names = []
uriNames = []
prKeys = {}
prFiles = {}

text=open(sys.argv[1], "r").read().split('\n')
for line in text:
    match = re.match(nameRe, line)
    if match:
        prName = match.group(1)
        if prName != printPlugin:
    	   names.append(prName)

files = os.popen('ls -tr ' + dbuildProjects).read().split('\n')
for fileName in files:
    for prName in names:
        if prName in fileName:
            prKeys[prName] = fileName
            break

for prName in names:
    prFiles[prName] = 'file://' + dbuildProjects + prKeys.get(prName, prName)

newText = []

for line in text:
    match = re.match(uriRe, line)
    if match:
        uriName = match.group(1)
        for name in names:
        #if prName != printPlugin:
            if name in uriName:
                 line = line.replace(uriName, prFiles.get(name, uriName))
    newText.append(line)

resultStr = '\n'.join(newText)   
print(resultStr)  
	
(fd, tempFileName) = tempfile.mkstemp()
try:
    tfile = os.fdopen(fd, "w")
    tfile.write(resultStr)
    tfile.close()
    call([dbuildExec, tempFileName])
finally:
    os.remove(tempFileName)
