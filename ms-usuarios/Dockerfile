FROM openjdk:21-jdk-slim AS builder

WORKDIR /app/ms-usuarios

COPY ./pom.xml /app
COPY ./ms-usuarios/.mvn ./.mvn
COPY ./ms-usuarios/mvnw .
COPY ./ms-usuarios/pom.xml .



RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./ms-usuarios/src ./src
RUN ./mvnw clean package

FROM openjdk:21-jdk-slim
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-usuarios/target/ms-usuarios-0.0.1-SNAPSHOT.jar .
ENV PORT=8001
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "ms-usuarios-0.0.1-SNAPSHOT.jar"]
#CMD ["sh", "-c", "java -jar ms-usuarios-0.0.1-SNAPSHOT.jar --server.port=$PORT"]