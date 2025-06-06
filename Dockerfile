####################  Stage 1 · Build  ####################
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .

# Build with a heap cap so Maven itself can’t OOM
RUN chmod +x ./mvnw \
 && MAVEN_OPTS="-Xmx512m" ./mvnw clean package -DskipTests

####################  Stage 2 · Runtime  ###################
FROM eclipse-temurin:17-jre AS runtime   # JRE is much smaller

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Limit JVM to ~75 % of container RAM (≈380 MB on Hobby plan)
CMD ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]