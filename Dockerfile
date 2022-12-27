FROM openjdk:17-jdk-alpine
MAINTAINER Korotkevich Mariya
COPY target/test_Inside-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]