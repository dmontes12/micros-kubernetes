FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/micro-cursos

COPY ./pom.xml /app
COPY ./micro-cursos/.mvn ./.mvn
COPY ./micro-cursos/mvnw .
COPY ./micro-cursos/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./micro-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/micro-cursos/target/micro-cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

ENTRYPOINT ["java", "-jar", "micro-cursos-0.0.1-SNAPSHOT.jar"]