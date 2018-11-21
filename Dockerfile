FROM openjdk:8-jre-alpine

MAINTAINER Bárányos András "baranyos.andras@gmail.com"

VOLUME /tmp

ADD warrant-web/target/routing-system-[0-9].[0-9]-RELEASE.jar rsservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/rsservice.jar"]
