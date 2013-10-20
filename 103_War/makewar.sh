#!/bin/bash

JETTY_VERSION="9.0.4.v20130625"
JETTY_DIR=/Volumes/HD/Install/jetty-distribution-${JETTY_VERSION}

CP="bin"
if [ ! -d WEB-INF/classes/fr ] ; then
  echo "classes does not exists, compile first."
  exit 1
fi
jar cvf todo.war www WEB-INF

