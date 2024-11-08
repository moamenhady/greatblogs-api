FROM eclipse-temurin:21-jre

LABEL authors="moamenhady <moamenhady@outlook.com>"

WORKDIR /opt/app/

COPY env.properties target/greatblogs-api-1.2.3.jar /opt/app/

EXPOSE 8080

CMD ["java", "-jar", "greatblogs-api-1.2.3.jar"]