# Use an official Maven image to build the app
FROM maven:3.8.1-jdk-11 AS build

# Set working directory in the container
WORKDIR /src

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean install

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
