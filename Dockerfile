FROM openjdk:8-jre-alpine

MAINTAINER Bárányos András "baranyos.andras@gmail.com"

WORKDIR /app

# Copy the current directory contents into the container at /app
ADD ./warrant-web/target/warrant-web-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080
EXPOSE 3306

# Run the app when the container launches
CMD java -jar warrant-web-0.0.1-SNAPSHOT.jar