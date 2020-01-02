# Automation Testing Template

A template for API and front end testing using Cucumber JVM.

# Pre-requistes

In order to run the tests you will need JDK 8 and Maven 3.6.1.
You will need to download the Chrome and Gecko web drivers and place them in the src/test/resources/webdrivers/mac and 
src/test/resources/webdrivers/windows folders.  This is so that this repo can be a git template as git will not accept 
any files over 10mb in a template.

# Run Tests

In your favourite IDE run the file 'Default.java'.

# Run Tests in Parallel

Using a command line tool, navigate to the root directory containing the 'pom.xml' file then run the command 
'mvn clean install'.
This will run 4 runners (marked Runner{number}.java) in parallel.  
Each runner runs a specified tag.  
These tags can be found above the scenarios in the '*.feature' files.
Details of the parallel running can be found in the failsafe plugin section of 'pom.xml' file. 

# Run Tests for a Specified Environment

In the 'common.properties' file change the value of the 'environment' property to be either 'local', 'production', 'stage' or 
'uat'.
This will then load the config from the relevant environment properties file marked '{environment}.properties'.
Any property value in an '{environment}.properties' file will override its equivalent property value in 
'common.properties'.

# Run Tests for a Specified Browser

In the 'common.properties' file change the value of the 'browser' property to be either 'chrome', 'firefox' or 'edge'.
You can also add a 'browser' property to one of the '{environment}.properties' files to run a specific browser for a particular 
environment.

Please note that at time of writing (4/6/19) Edge is not currently supported on MacOS.

# Reporting

This project uses Cluecumber for reports: https://github.com/trivago/cluecumber-report-plugin.
Simply run your tests then open 'target/cucumber-report/index.html' in a browser to view the test results report.

# Known Issues

Currently parallel running does not work for the Edge browser.