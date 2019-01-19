FROM openjdk:8-jdk-alpine

WORKDIR /code/
COPY ./target/fati.jar .

EXPOSE 8080

CMD java -jar fati.jar --server.port=8080