#!/bin/bash
JGLib="../JGLib"
Main="JBreakout"

rm -f "$JGLib"/*.class *.class
javac -encoding UTF-8 -cp "${JGLib}:." *.java
cp "$JGLib"/*.class .
jar cvfe "${Main}.jar" "$Main" *.class *.wav
