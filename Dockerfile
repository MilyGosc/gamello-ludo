FROM adoptopenjdk/openjdk11:alpine-slim
ARG JAR_FILE
COPY ${JAR_FILE} ludo.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/ludo.jar"]
EXPOSE 8080