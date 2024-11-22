# Use uma imagem base do Maven para construir
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Copie os arquivos para o container
COPY . /app

# Defina o diretório de trabalho
WORKDIR /app

# Execute o build do projeto
RUN mvn clean package

# Use uma imagem mais leve para rodar
FROM openjdk:17-jdk-slim

# Copie o JAR do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Execute o aplicativo
CMD ["java", "-jar", "app.jar"]
