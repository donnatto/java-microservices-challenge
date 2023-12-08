FROM eclipse-temurin:17-jdk-alpine
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java -jar app.jar