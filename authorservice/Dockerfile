FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 6003
COPY target/authorservice-0.0.1-SNAPSHOT.jar authorservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "authorservice-0.0.1-SNAPSHOT.jar"]