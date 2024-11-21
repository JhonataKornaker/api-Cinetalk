# Usar a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Execute o build do projeto
RUN mvn clean package

# Copiar o arquivo JAR gerado para dentro do container
COPY target/api-cinetalk-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor a porta onde a aplicação vai rodar
EXPOSE 8080

# Definir o comando para rodar a aplicação
CMD ["java", "-jar", "/app/app.jar"]
