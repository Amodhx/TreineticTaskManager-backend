FROM ubuntu:latest
LABEL authors="ACER"
FROM openjdk:21-slim
EXPOSE 5050
ADD target/TreineticTaskManager.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
