FROM openjdk:25-jdk-slim

WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Expose port 8080 (Spring Boot default port)
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev
# Command to run your Spring Boot app
CMD ["./mvnw", "spring-boot:run", "-Dspring.devtools.restart.enabled=true"]