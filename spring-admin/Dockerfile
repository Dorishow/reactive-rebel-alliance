FROM openjdk:17-jdk-alpine
 
ENV APP_REFERENCE_JAR=target/spring-admin.jar
ENV APP_NAME=spring-admin.jar
 
COPY ${APP_REFERENCE_JAR} ${APP_NAME}
ENTRYPOINT java -jar ${APP_NAME}