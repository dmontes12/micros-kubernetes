FROM openjdk:17-alpine3.14

WORKDIR /app

COPY ./target/micro-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java","-jar","micro-usuarios-0.0.1-SNAPSHOT.jar"]

