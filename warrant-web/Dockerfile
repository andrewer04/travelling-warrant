FROM openjdk:8-jre-alpine

MAINTAINER Bárányos András "baranyos.andras@gmail.com"

VOLUME /tmp

ARG JAR_FILE=target/routing-system-0.9-RELEASE.jar
ADD ${JAR_FILE} rsservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/rsservice.jar"]
