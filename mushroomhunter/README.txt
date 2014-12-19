Mushroom-Hunter-Portal
======================

PA165 Team Project - Mushroom Hunter Portal - Web Application

Project Description
---------------------
This project contains web application developed as school team project. 
Application is runnable on embedded Tomcat 7 using Maven 3. The installation of 
external Derby database server is needed.

Prerequisites 
--------------------
1)  Java 7 installed
2)  Maven 3 installed
3)  Path to Maven and Java configured in environment variables
4)  Derby database running on port 1527 and accessible with username "pa165" 
    and password "pa165"
     
How to run project
--------------------
1)  Open command line and go to web module project 
    (REPOSITORY_BASE/mushroomhunter/mushroomhunter-Web)
2)  Execute command:
    mvn tomcat7:run
    
Note: Application will be available on http://localhost:8080/pa165
    
How to run JUnit tests
----------------------
1)  Open command line and go to Mushroom Hunter parent project folder 
    (REPOSITORY_BASE/mushroomhunter)
2)  Execute command:
    mvn test
