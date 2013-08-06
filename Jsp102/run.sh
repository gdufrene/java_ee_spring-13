#!/bin/bash

JETTY_VERSION="9.0.4.v20130625"
JETTY_DIR=/Volumes/HD/Install/jetty-distribution-9.0.4.v20130625

CP="bin"
for jettylib in server servlet util http io security webapp xml ; do
  CP="$CP:$JETTY_DIR/lib/jetty-$jettylib-$JETTY_VERSION.jar"
done
for lib in $(ls $JETTY_DIR/lib/jsp/*.jar) ; do
  CP="$CP:$lib"
done
CP="$CP:$JETTY_DIR/lib/servlet-api-3.0.jar"

test -d "bin" || mkdir "bin"
javac -classpath "$CP" -sourcepath "src" -d "bin" "src/fr/eservice/server/Run.java"
java -Dfile.encoding=UTF-8 -cp "$CP" fr.eservice.server.Run
