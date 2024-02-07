# Etapa 1
# chamado `build-image`, imagem JDK temporária para compilar o código
# define o diretório de trabalho
# dependency:go-offline -> baixa e armazena cache local que agiliza o processo de re-criação da imagem.
# -DskipTests: evita ter problemas com os testes durante a construção da sua imagem.
FROM maven:3-openjdk-17 as build-image
WORKDIR /to-build-app
COPY . .
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

# Etapa 2
# imagem JRE, limpa e mais leve
# copia o jar gerado no primeiro estágio para o diretório de trabalho
# expõe a porta 8080
# define o comando de execução da aplicação
FROM eclipse-temurin:17-jre-alpine
COPY --from=build-image /to-build-app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]