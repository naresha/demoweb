FROM gradle:8.13-jdk17 AS build
LABEL authors="naresha"
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/build/libs/demo-1.0.0.war .
ENTRYPOINT ["java", "-jar", "demo-1.0.0.war"]