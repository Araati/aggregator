FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} aggregator.jar
ENTRYPOINT ["java","-jar","/aggregator.jar"]