#!/bin/bash

if [ ! -d "apache-maven-3.1.1" ] ; then
  wget http://apache.websitebeheerjd.nl/maven/maven-3/3.1.1/binaries/apache-maven-3.1.1-bin.tar.gz
  tar zxvf apache-maven-3.1.1-bin.tar.gz
fi

test -L mvn || ln -s apache-maven-3.1.1/bin/mvn
