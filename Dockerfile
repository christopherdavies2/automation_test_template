FROM:
    debian
    maven
    openjdk:8-jre

COPY ./ ./

RUN mvn clean install


