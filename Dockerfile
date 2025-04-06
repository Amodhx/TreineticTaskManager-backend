FROM ubuntu:latest
LABEL authors="ACER"
FROM openjdk:17-slim
ADD target/TreineticTaskManager.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
