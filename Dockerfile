FROM openjdk:8u121-jdk-alpine
COPY "./target/identity-service-0.0.1-SNAPSHOT.jar" "./identity-service.jar"
EXPOSE 8085
run ["chmod", "+x", "identity-service.jar"]
ENTRYPOINT ["java","-jar","./identity-service.jar"]