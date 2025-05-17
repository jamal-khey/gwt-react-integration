@echo off
REM Path to Maven installation
set MAVEN_HOME=C:\dev\plateform\maven\apache-maven-3.2.5

set JAVA_HOME=C:\dev\plateform\java\jdk1.8.0_72
REM Use custom settings.xml and repository location
"%MAVEN_HOME%\bin\mvn" -s "C:\dev\plateform\maven\apache-maven-3.2.5settings.xml" -Dmaven.repo.local="C:\dev\plateform\maven\testrepo" %*