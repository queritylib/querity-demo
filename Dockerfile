FROM eclipse-temurin:21

RUN ./mvnw clean package
COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
