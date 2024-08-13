FROM maven:3.8.6-openjdk-1.8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

FROM openjdk:18-jre-slim
WORKDIR /app
COPY --from=build /app/target/meu-projeto.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
