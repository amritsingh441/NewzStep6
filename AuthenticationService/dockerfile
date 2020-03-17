FROM openjdk:11-jdk
WORKDIR usr/src
ADD ./target/AuthenticationService-0.0.1-SNAPSHOT.jar /usr/src/AuthenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","AuthenticationService-0.0.1-SNAPSHOT.jar"]