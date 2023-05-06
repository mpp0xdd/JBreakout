@echo off
setlocal
set JGLib=..\JGLib
set Classes=classes
set Main=JBreakout

del "%Classes%\*.class"
javac -d "%Classes%" -encoding UTF-8 -cp "%JGLib%;." *.java
jar cvfe "%Main%.jar" "%Main%" -C %Classes% .
