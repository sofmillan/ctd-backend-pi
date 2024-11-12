FROM openjdk:17-jdk
EXPOSE 8080 
COPY build/libs/backend-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]

