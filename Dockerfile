# Use a base image with Java 17 (or your required version)
FROM openjdk:17-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the Maven or Gradle wrapper and the project files
COPY pom.xml ./
COPY src ./src

# Download Maven dependencies (skip tests to speed up build)
RUN ./mvnw dependency:go-offline -B

# Package the application
RUN ./mvnw package -DskipTests

# Use a smaller base image to run the application
FROM openjdk:17-jre-slim

# Set the working directory in the new image
WORKDIR /app

# Copy the jar file from the builder image
COPY --from=builder /app/target/your-app-name.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
