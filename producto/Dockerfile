# ============================================
# Dockerfile genérico para microservicios Spring Boot (Java 21)
# Este MISMO archivo se copia, sin modificar, dentro de
# CADA carpeta de microservicio (boleta/, cliente/, venta/, etc.)
# ============================================

# ---------- Etapa 1: build con Maven ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la cache de capas de Docker
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- Etapa 2: imagen final, liviana, solo runtime ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el jar generado (el nombre exacto varía por servicio, por eso el *)
COPY --from=build /app/target/*.jar app.jar

# Puerto interno del contenedor (el real se define via variable de entorno SERVER_PORT
# o queda el que ya trae application.properties)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
