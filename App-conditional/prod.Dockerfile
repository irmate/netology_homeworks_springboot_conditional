FROM adoptopenjdk:11-jdk-hotspot
EXPOSE 8081
ADD target/App-conditional-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]