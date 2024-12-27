FROM openjdk:21-jdk-slim AS builder

WORKDIR /app/ms-cursos

COPY ./pom.xml /app
COPY ./ms-cursos/.mvn ./.mvn
COPY ./ms-cursos/mvnw .
COPY ./ms-cursos/pom.xml .


RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./ms-cursos/src ./src
RUN ./mvnw clean package

FROM openjdk:21-jdk-slim
WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-cursos/target/ms-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

#ENTRYPOINT ["java", "-jar", "ms-usuarios-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "ms-cursos-0.0.1-SNAPSHOT.jar"]