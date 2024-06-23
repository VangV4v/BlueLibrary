FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 9005 6005
COPY target/imageservice-0.0.1-SNAPSHOT.jar imageservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "imageservice-0.0.1-SNAPSHOT.jar"]