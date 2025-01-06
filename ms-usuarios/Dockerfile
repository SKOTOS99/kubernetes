ARG APP_NAME=ms-usuarios
FROM openjdk:21-jdk-slim AS builder

ARG APP_NAME
WORKDIR /app/$APP_NAME

COPY ./pom.xml /app
COPY ./$APP_NAME/.mvn ./.mvn
COPY ./$APP_NAME/mvnw .
COPY ./$APP_NAME/pom.xml .



RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./$APP_NAME/src ./src
RUN ./mvnw clean package

FROM openjdk:21-jdk-slim
ARG APP_NAME
WORKDIR /app
RUN mkdir ./logs

ARG TARGET_PATH=/app/$APP_NAME/target
COPY --from=builder $TARGET_PATH/$APP_NAME-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8001
ENV PORT=$PORT_APP
EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "ms-usuarios-0.0.1-SNAPSHOT.jar"]
#CMD ["sh", "-c", "java -jar ms-usuarios-0.0.1-SNAPSHOT.jar --server.port=$PORT"]