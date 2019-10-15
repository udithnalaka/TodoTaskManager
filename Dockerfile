FROM openjdk:8
EXPOSE 8080
ADD target/TodoTaskManager-0.0.1-SNAPSHOT.jar TodoTaskManager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TodoTaskManager-0.0.1-SNAPSHOT.jar"]