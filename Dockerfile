# Use a base image with OpenJDK and Alpine Linux
FROM eclipse-temurin:21-jre-alpine

LABEL authors="moamenhady <moamenhady@outlook.com>"

# Set the working directory inside the container
WORKDIR /app

COPY env.properties /app/env.properties

# Copy the JAR file into the container at /app
COPY target/greatblogs-api-1.2.2.jar /app/greatblogs-api.jar

# Expose the port that your Spring Boot app is running on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "greatblogs-api.jar"]

