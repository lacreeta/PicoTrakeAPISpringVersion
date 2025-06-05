FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

ENV PORT=8080

EXPOSE 8080

CMD ["java", "-jar", "target/API-0.0.1-SNAPSHOT.jar"]
