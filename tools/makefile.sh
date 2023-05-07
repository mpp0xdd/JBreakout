#!/bin/bash
CFLAGS="-J-Dfile.encoding=UTF-8"
JFLAGS="-Dfile.encoding=UTF-8"
JGLIB="../JGLib"
CLASSES="classes"
MAIN="JBreakout"
FORMATTER="../Lib/google-java-format-1.15.0-all-deps.jar"

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
  java "$JFLAGS" -jar "$FORMATTER" -i *.java
}

clean () {
  rm -f "$CLASSES"/*.class
}

make () {
  javac -d "$CLASSES" "$CFLAGS" -cp "${JGLIB}:." *.java
}

run () {
  java "$JFLAGS" -cp "$CLASSES" "$MAIN"
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
