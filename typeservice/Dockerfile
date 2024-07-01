FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 6001
COPY target/typeservice-0.0.1-SNAPSHOT.jar typeservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "typeservice-0.0.1-SNAPSHOT.jar"]