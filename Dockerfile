# ---------- Stage 1: Build ----------
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x ./mvnw \
 && MAVEN_OPTS="-Xmx512m" ./mvnw clean package -DskipTests \
 && ./mvnw dependency:purge-local-repository -DmanualInclude="*" -DreResolve=false || true

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
