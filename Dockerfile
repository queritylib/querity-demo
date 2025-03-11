FROM eclipse-temurin:21 as build

COPY . /src
WORKDIR /src
RUN ./mvnw clean package

FROM eclipse-temurin:21

COPY --from=build /src/target/*.jar /app.jar

CMD ["java", "-jar", "app.jar"]
