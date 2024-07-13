# Use a multi-stage build for cleaner images
# Stage 1: Build
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-alpine
LABEL authors="moamenhady <moamenhady@outlook.com>"

WORKDIR /app
COPY env.properties /app/env.properties
COPY --from=build /app/target/greatblogs-api-1.2.2.jar /app/greatblogs-api.jar

# Expose the port that your Spring Boot app is running on
EXPOSE 8080

# Command to run your application
ENTRYPOINT ["java", "-jar", "greatblogs-api.jar"]