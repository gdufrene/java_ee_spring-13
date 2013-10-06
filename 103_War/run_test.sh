#!/bin/bash

JUNIT_VERSION="4.11"
HAMCREST_VERSION="1.3"

#Check or download junit.jar
if [ ! -f junit-${JUNIT_VERSION}.jar ] ; then
  wget "http://repo1.maven.org/maven2/junit/junit/${JUNIT_VERSION}/junit-${JUNIT_VERSION}.jar" \
  || echo "Unable to get Junit $JUNIT_VERSION" \
  || exit 1
fi

#Check or download
if [ ! -f hamcrest-core-${HAMCREST_VERSION}.jar ] ; then
  wget "http://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/${HAMCREST_VERSION}/hamcrest-core-${HAMCREST_VERSION}.jar" \
  || echo "Unable to get Hamcrest $HAMCREST_VERSION" \
  || exit 2
fi

if [ ! -d bin/fr ] ; then
  echo "classes does not exists, compile first."
  exit 3
fi

CP="bin:junit-${JUNIT_VERSION}.jar:hamcrest-core-${HAMCREST_VERSION}.jar"

#Compile Tests
javac -classpath "$CP" -sourcepath src:test -d bin test/todo/AllTests.java
java -cp "$CP" org.junit.runner.JUnitCore todo.AllTests \
 | grep -v org.junit \
 | grep -v sun.reflect \
 | grep -v lang.reflect 

