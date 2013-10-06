#!/bin/bash

JETTY_VERSION="9.0.4.v20130625"
JETTY_DIR=/Volumes/HD/Install/jetty-distribution-${JETTY_VERSION}

CP="bin"
#for jettylib in server servlet util http io security ; do
#  CP="$CP:$JETTY_DIR/lib/jetty-$jettylib-$JETTY_VERSION.jar"
#done
CP="$CP:$JETTY_DIR/lib/servlet-api-3.0.jar"

test -d "bin" || mkdir "bin"
javac -classpath "$CP" -sourcepath "src" -d "bin" "src/fr/eservice/todo/TodoServlet.java"
