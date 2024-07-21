FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 6008
COPY target/adminservice-0.0.1-SNAPSHOT.jar adminservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "adminservice-0.0.1-SNAPSHOT.jar"]