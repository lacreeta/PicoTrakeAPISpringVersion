FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Copia el .jar generado con nombre genérico para evitar errores con versiones
RUN cp target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]