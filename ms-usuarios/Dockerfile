# Imagen base de OpenJDK
FROM openjdk:21-jdk-slim

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Maven
COPY ./target/*.jar app.jar

# Exponer el puerto donde Spring Boot ejecutará la API
EXPOSE 8001

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]