# Usar a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado para dentro do container
COPY target/api-cinetalk-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor a porta onde a aplicação vai rodar
EXPOSE 8081

# Definir o comando para rodar a aplicação
CMD ["java", "-jar", "/app/app.jar", "--server.port=${PORT:8081}"]
