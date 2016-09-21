#!/usr/bin/env bash

cd src
rmiregistry &
javac cl/uchile/dcc/cc5303/*.java
javac cl/uchile/dcc/cc5303/entities/*.java
