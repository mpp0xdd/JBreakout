#!/bin/bash
JGLib="../JGLib"
Main="JBreakout"

javac -encoding UTF-8 -cp "$JGLib:." *.java
cp "$JGLib"/Game*.class .
jar cvfe "$Main.jar" "$Main" *.class
