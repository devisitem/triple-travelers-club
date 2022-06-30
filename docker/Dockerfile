FROM openjdk:8-jdk-alpine
RUN mkdir /service
RUN mkdir /service/config
ADD ../src/main/resources/application.yml /service/config
ADD ../target/*.jar /service/app.jar
ENTRYPOINT ["java", "-Dspring.config.location=/service/config/", "-jar", "/service/app.jar"]




