# Use an official Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory in the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean install -DskipTests


# Use an official Java image to run the app
FROM openjdk:11-jre-slim

# Set working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on (default Spring Boot is 8080)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
