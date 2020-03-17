FROM openjdk:11-jdk
EXPOSE 8084
ADD ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]