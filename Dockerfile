FROM maven:3.9.9-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests
#
# Package stage
#
FROM openjdk:21-jdk-slim
COPY --from=build /target/media-cataloger-api.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]