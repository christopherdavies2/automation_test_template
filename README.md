# Front End Testing Template

A template for front end testing using Cucumber JVM, Selenium and Spring.

# Pre-requistes

In order to run the tests you will need JDK 8 and Maven 3.6.1.

# Run Tests

In your favourite IDE run the file 'Default.java'.

# Run Tests in Parallel

Using a command line tool, navigate to the root directory containing the 'pom.xml' file then run the command 
'mvn clean install'.
This will run 4 runners (marked Runner{number}.java) in parallel.  
Each runner runs a specified tag.  These tags can be found above the scenarios in the 'prime.number.page.feature' file.
Details of the parallel execution can be found in the 'pom.xml' file. 

# Run Tests for a Specified Environment

In the 'default.properties' file change the value of the 'environment' property to be either 'production', 'stage' or 
'uat'.
This will then load the config from the relevant environment properties file marked '{environment}.properties'.
Any property value in an '{environment}.properties' file will override its equivalent property value in 
'default.properties'.