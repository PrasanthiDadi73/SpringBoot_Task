FROM openjdk:latest
EXPOSE 9999
COPY target/SpringBootDocker.jar SpringBootDocker.jar
ENTRYPOINT ["java", "-jar","/SpringBootDocker.jar"]