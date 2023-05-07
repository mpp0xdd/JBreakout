@echo off
setlocal
set JGLIB=..\JGLib
set CLASSES=classes
set MAIN=JBreakout

del "%CLASSES%\*.class"
javac -d "%CLASSES%" -cp "%JGLIB%;." -encoding UTF-8 *.java
jar cvfe "%MAIN%.jar" "%MAIN%" -C %CLASSES% .
