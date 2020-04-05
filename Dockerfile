FROM maven:3.6.0-jdk-8 AS build
WORKDIR /usr/share/wow
ENTRYPOINT mvn clean test -Dbrowser.name=chrome


