FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/TaskManagementSystem-0.0.1-SNAPSHOT.jar /app/management.jar
ENTRYPOINT ["java", "-jar", "management.jar"]