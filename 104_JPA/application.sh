#!/bin/bash

CP="bin"

for jar in $(find lib -name "*.jar") ; do
  CP="$CP:$jar"
done

java -cp "$CP" fr.eservice.common.Application

