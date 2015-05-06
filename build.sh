#!/bin/bash

CLASSPATH=$(find lib -name '*.jar' | xargs echo | tr ' ' ':')
mkdir -p target/classes
javac -cp $CLASSPATH src/com/example/app/*.java -d target/classes
jar -cf target/SimpleJavaApp.jar -C target/classes/ .
