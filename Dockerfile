FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER apps.saranganrajan.com
COPY target/ahmf-manager-0.0.1-SNAPSHOT.jar ahmf-manager.jar
ENTRYPOINT ["java","-jar","/ahmf-manager.jar"]