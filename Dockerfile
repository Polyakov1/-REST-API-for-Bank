# Build stage
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/banking-api.jar ./app.jar
COPY --from=build /app/target/classes/db ./db

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]