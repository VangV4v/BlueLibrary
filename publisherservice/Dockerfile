FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 6002
COPY target/publisherservice-0.0.1-SNAPSHOT.jar publisherservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "publisherservice-0.0.1-SNAPSHOT.jar"]