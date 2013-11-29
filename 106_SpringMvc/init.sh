#!/bin/bash

if [ ! -f www/bootstrap.zip ] ; then
  echo "Download bootstrap."
  wget -P www/ http://getbootstrap.com/2.3.2/assets/bootstrap.zip
fi

if [ ! -d www/bootstrap ] ; then
  echo "Unzip bootstrap."
  cd www
  unzip bootstrap.zip
  cd ..
fi

if [ ! -d www/jquery ] ; then
  echo "Download jquery."
  mkdir www/jquery
  cd www/jquery
  wget http://code.jquery.com/jquery.min.js
  cd ../..
fi

echo "Ok."
