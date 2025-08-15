# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
# copy the fat jar (adjust name if your artifact is different)
COPY --from=build /app/target/*.jar app.jar

# expose the port Spring Boot listens on
EXPOSE 8080
# make Actuator health not required to start; the app itself will start
ENTRYPOINT ["java","-jar","/app/app.jar"]
