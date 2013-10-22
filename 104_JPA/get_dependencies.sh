#!/bin/bash

server="http://repo1.maven.org/maven2/"
files="org/hibernate/hibernate-core/4.2.6.Final/hibernate-core-4.2.6.Final.jar 
org/hibernate/common/hibernate-commons-annotations/4.0.2.Final/hibernate-commons-annotations-4.0.2.Final.jar 
org/hibernate/hibernate-entitymanager/4.2.6.Final/hibernate-entitymanager-4.2.6.Final.jar 
org/hibernate/javax/persistence/hibernate-jpa-2.0-api/1.0.1.Final/hibernate-jpa-2.0-api-1.0.1.Final.jar 
antlr/antlr/2.7.7/antlr-2.7.7.jar 
dom4j/dom4j/1.6.1/dom4j-1.6.1.jar 
org/javassist/javassist/3.15.0-GA/javassist-3.15.0-GA.jar 
org/jboss/logging/jboss-logging/3.1.0.GA/jboss-logging-3.1.0.GA.jar 
org/jboss/spec/javax/transaction/jboss-transaction-api_1.1_spec/1.0.1.Final/jboss-transaction-api_1.1_spec-1.0.1.Final.jar"

test -d lib/hibernate || mkdir -p lib/hibernate

for file in $files ; do
  wget -P lib/hibernate $server$file
done

wget -P lib "http://central.maven.org/maven2/org/hsqldb/hsqldb/2.3.1/hsqldb-2.3.1.jar"

