#!/bin/bash

CP=""

for jar in $(find lib -name "*.jar") ; do
  CP="$CP:$jar"
done

javac -d bin -sourcepath src:test -cp "$CP" \
src/fr/eservice/common/Application.java \
src/fr/eservice/jdbc/EtudiantJdbcDao.java \
src/fr/eservice/jpa/EtudiantJPADao.java

