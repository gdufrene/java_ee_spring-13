#!/bin/bash

JETTY_VERSION="9.0.4.v20130625"
JETTY_DIR=/Volumes/HD/Install/jetty-distribution-${JETTY_VERSION}

CP="bin"
if [ ! -d bin/fr ] ; then
  echo "classes does not exists, compile first."
  exit 1
fi
test -d WEB-INF/classes && rm -rf WEB-INF/classes 
mkdir WEB-INF/classes
cp -r bin/fr WEB-INF/classes
jar cvf todo.war www WEB-INF

test -d "bin" || mkdir "bin"
javac -classpath "$CP" -sourcepath "src" -d "bin" "src/fr/eservice/todo/TodoList.java"
