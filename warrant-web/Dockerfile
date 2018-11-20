FROM openjdk:8-jre-alpine

MAINTAINER Bárányos András "baranyos.andras@gmail.com"

VOLUME /tmp

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/rsservice/lib

ARG JAR_FILE=target/routing-system-0.9-RELEASE.jar
ADD ${JAR_FILE} /usr/share/rsservice/rsservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/rsservice/rsservice.jar"]
