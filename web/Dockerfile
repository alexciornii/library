FROM openjdk:8-jdk-alpine
ADD web/target/web-0.0.1-SNAPSHOT.jar web-0.0.1-SNAPSHOT.jar.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "web-0.0.1-SNAPSHOT.jar"]