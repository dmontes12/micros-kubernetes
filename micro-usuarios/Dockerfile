FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/micro-usuarios

COPY ./pom.xml /app
COPY ./micro-usuarios/.mvn ./.mvn
COPY ./micro-usuarios/mvnw .
COPY ./micro-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./micro-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/micro-usuarios/target/micro-usuarios-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

CMD ["java", "-jar", "micro-usuarios-0.0.1-SNAPSHOT.jar"]