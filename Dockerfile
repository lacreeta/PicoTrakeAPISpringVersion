# ---------- Etapa 1: build ----------
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x ./mvnw \
 && MAVEN_OPTS="-Xmx512m" ./mvnw clean package -DskipTests

# ---------- Etapa 2: runtime ----------
FROM eclipse-temurin:17-jre AS runtime   # JRE = mucho más pequeño

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# puerto interno estándar
EXPOSE 8080

# Limitar heap y dejar a la JVM ajustar el resto
CMD ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
