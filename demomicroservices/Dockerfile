# ----- Etapa 1: BUILD -----
# Usamos una imagen con el Kit de Desarrollo de Java (JDK) y Maven
FROM maven:3.9-eclipse-temurin-21 AS build
LABEL authors="spart"

WORKDIR /src

# Copiamos solo el pom.xml para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente
COPY src src

# Construimos la aplicación (Quarkus fast-jar por defecto)
RUN mvn package -DskipTests

# ----- Etapa 2: FINAL -----
# Empezamos desde una imagen limpia y ligera de JRE
FROM eclipse-temurin:21-jre-jammy

RUN addgroup --system appgroup && \
    adduser --system --ingroup appgroup --no-create-home appuser

WORKDIR /app

# Copiamos los artefactos construidos desde la etapa "build"
COPY --from=build /src/target/*.jar app.jar

RUN chown -R appuser:appgroup /app
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]