FROM openjdk:21-jdk-slim

WORKDIR /app/ms-usuarios

COPY ./pom.xml /app
COPY ./ms-usuarios/.mvn ./.mvn
COPY ./ms-usuarios/mvnw .
COPY ./ms-usuarios/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./ms-usuarios/src ./src
RUN ./mvnw clean package

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "./target/ms-usuarios-0.0.1-SNAPSHOT.jar"]