FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/camunda-app.jar camunda-app.jar
ENTRYPOINT ["java","-jar","/camunda-app.jar"]