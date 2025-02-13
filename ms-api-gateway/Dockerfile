FROM openjdk:21-jdk-slim AS builder

WORKDIR /app/ms-api-gateway

COPY ./pom.xml /app
COPY ./ms-api-gateway/.mvn ./.mvn
COPY ./ms-api-gateway/mvnw .
COPY ./ms-api-gateway/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./ms-api-gateway/src ./src
RUN ./mvnw clean package

FROM openjdk:21-jdk-slim
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-api-gateway/target/ms-api-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8090

#ENTRYPOINT ["java", "-jar", "ms-usuarios-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "ms-api-gateway-0.0.1-SNAPSHOT.jar"]