FROM adoptopenjdk/openjdk11:alpine-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
