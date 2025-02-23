# Use official OpenJDK 17 as base image
FROM openjdk:17-jdk-slim AS builder

# Set working directory
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Build the application
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Final image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/spring-boot-jwt-auth-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]