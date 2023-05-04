@echo off
setlocal
set JGLib=..\JGLib
set Main=JBreakout

del "%JGLib%\*.class" *.class
javac -encoding UTF-8 -cp "%JGLib%;." *.java
copy "%JGLib%\*.class" .
jar cvfe "%Main%.jar" "%Main%" *.class %*