# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Configura o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR para o container
COPY target/minhaaplicacao-1.0-SNAPSHOT.jar /app/minhaaplicacao.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/minhaaplicacao.jar"]
