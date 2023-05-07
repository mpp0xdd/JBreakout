#!/bin/bash
CFLAGS="-J-Dfile.encoding=UTF-8"
JGLIB="../JGLib"
CLASSES="classes"
MAIN="JBreakout"

rm -f "$CLASSES"/*.class
javac -d "$CLASSES" "$CFLAGS" -cp "${JGLIB}:." *.java
jar "$CFLAGS" cvfe "${MAIN}.jar" "$MAIN" -C "$CLASSES" .
