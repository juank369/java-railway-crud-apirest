# Etapa de compilación
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app
COPY . /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN cp target/*.jar app.jar

# Etapa de ejecución
FROM eclipse-temurin:21-jre

# Railway asigna el puerto dinámicamente, aseguramos que Java lo use
ENV PORT=8080

WORKDIR /app
COPY --from=build --chown=runtime:runtime /app/app.jar .

RUN useradd -m runtime
USER runtime

# Ejecutamos la app con el puerto asignado por Railway
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app/app.jar"]
