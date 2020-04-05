# Automation Testing Template

A template for API and front end testing using Cucumber JVM.

# Pre-requistes

In order to run the tests you will need JDK 8 and Maven 3.6.1.
You will need to download the Chrome and Gecko web drivers and place them in the 'src/test/resources/webdrivers/mac' and 
'src/test/resources/webdrivers/windows' folders, you will also need to strip off the .exe extension from the webdriver 
file.  
The drivers have been left out of the repo as a git template can not have any files over 10mb in a template.

# Run Tests

To run tests one at a time, run the file 'Default.java' in JUnit.  Tests will run 
in the production environment by default as specified in the 'environment' property in 'common.properties'.  

# Run Tests in Parallel

Using a command line tool, navigate to the root directory containing the 'pom.xml' file then run the command 
'mvn clean install'.
This will run 4 runners (marked Runner{number}.java) in parallel.  
Each runner runs a specified tag.  
These tags can be found above the scenarios in the '*.feature' files.
Details of the parallel running can be found in the failsafe plugin section of 'pom.xml' file. 

# Run Tests for a Specified Environment

In the 'common.properties' file change the value of the 'environment' property to be either 'local', 'production', 
'stage' or 'uat'.
This will then load the config from the relevant environment properties file marked '{environment}.properties'.
Any property value in an '{environment}.properties' file will override its equivalent property value in 
'common.properties'.

When running via Maven you can specify the environment using the '-Denv' variable when running e.g. 'mvn clean install 
-Denv=uat' to run the tests in the UAT environment.

# Run Tests for a Specified Browser

In the 'common.properties' file change the value of the 'browser.name' property to be either 'chrome', 'firefox' or 
'edge'.

When running via Maven you can specify the browser using the '-Dbrowser' variable e.g. 'mvn clean install 
-Dbrowser=firefox' to run the tests in Firefox.

Please note that at time of writing (4/6/19) Edge is not currently supported on MacOS.

# Run Tests Headless

In the 'common.properties' file change the value of the 'browser.headless' property to be true.

When running via Maven you can specify running headless by using the '-Dheadless' variable e.g. 'mvn clean install 
-Dheadless=true'. 

# Reporting

This project uses Cluecumber for reports: https://github.com/trivago/cluecumber-report-plugin.
Simply run your tests then open 'target/cucumber-report/index.html' in a browser to view the test results report.

# Known Issues

Currently parallel running does not work for the Edge browser.