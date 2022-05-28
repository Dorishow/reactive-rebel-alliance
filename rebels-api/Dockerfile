FROM openjdk:17-jdk-alpine
 
ENV APP_REFERENCE_JAR=target/rebels.jar
ENV APP_NAME=rebels.jar
 
COPY ${APP_REFERENCE_JAR} ${APP_NAME}
ENTRYPOINT java -jar ${APP_NAME}

EXPOSE 8080