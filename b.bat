del *.class
del .\t\*.class
SET CP=.;.\t;.\t\junit-4.11.jar;.\t\hamcrest-core-1.3.jar

javac -classpath %CP% EightPuzzle.java
rem javac -classpath %CP% EightPuzzleSolver.java

javac -classpath %CP% .\t\TestEightPuzzle.java
javac -classpath %CP% .\t\TestAll.java

java -classpath %CP% TestAll
