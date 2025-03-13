FROM eclipse-temurin:21-jdk-alpine as build

COPY . /src
WORKDIR /src
RUN ./mvnw -Pwith-frontend clean package

FROM eclipse-temurin:21-jre-alpine

COPY --from=build /src/target/*.jar /app.jar

CMD ["java", "-jar", "app.jar"]
