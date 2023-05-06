#!/bin/bash
JGLib="../JGLib"
Classes="classes"
Main="JBreakout"

rm -f "$Classes"/*.class
javac -d "$Classes" -encoding UTF-8 -cp "${JGLib}:." *.java
jar cvfe "${Main}.jar" "$Main" -C "$Classes" .
