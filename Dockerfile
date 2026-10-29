# Etapa 1: build do projeto com Maven
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Etapa 2: imagem final só com o JRE e o JAR
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/pokemon-0.0.1-SNAPSHOT.jar app.jar
CMD ["sh", "-c", "java -jar app.jar"]
