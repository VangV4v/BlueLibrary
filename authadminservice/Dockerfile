FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 5003
COPY target/authadminservice-0.0.1-SNAPSHOT.jar authadminservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "authadminservice-0.0.1-SNAPSHOT.jar"]