#!/bin/bash
JGLib="../JGLib"
Classes="classes"
Main="JBreakout"
Formatter="../Lib/google-java-format-1.15.0-all-deps.jar"

usage () {
  cat 1>&2 <<EOF
    Usage: $0 [options]

    Options:
      -h      display this help
      -f      format source code
      -c      remove all class files
      -m      run compile
      -r      compile and execute
EOF
  exit
}

format () {
  java -jar "$Formatter" -i *.java
}

clean () {
  rm -f "$Classes"/*.class
}

make () {
  javac -d "$Classes" -encoding UTF-8 -cp "${JGLib}:." *.java
}

run () {
  java -cp "$Classes" "$Main"
  exit
}


if [ $# -eq 0 ]; then
  usage
fi

while getopts 'hfcmr' opt; do
  case "$opt" in
    h) usage ;;
    f) format ;;
    c) clean ;;
    m) make ;;
    r) make && run ;;
  esac
done
