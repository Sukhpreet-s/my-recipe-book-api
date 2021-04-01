FROM openjdk:8-jdk-alpine
MAINTAINER my-recipe-book-api.com
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]