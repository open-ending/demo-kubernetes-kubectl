FROM openjdk:11
LABEL authors="ellendan"

COPY ./app/build/libs/app.jar /workspace/app.jar
WORKDIR /workspace
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]