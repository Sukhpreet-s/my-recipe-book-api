FROM openjdk:8-jdk-alpine
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=72m","/app.jar"]