FROM maven:3.9.3-eclipse-temurin-20 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:20-jdk
COPY --from=build /usr/src/app/target/*.jar /usr/app/npvet-api-1.0.0-SNAPSHOT.jar
ENV API_PORT=8080
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_NOME=npvet
ENV DB_USER=postgres
ENV DB_PWD=postgres
ENV DDL=validate
ENV ISSUER_URI="http://keycloak:8080/auth/realms/npvet"
ENV JWT_SECRET="CHAVE_SECRETA_JWT"
ENV CLIENT_ID="npvet-api"
EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/app/npvet-api-1.0.0-SNAPSHOT.jar"]
