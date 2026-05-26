#Baixando o maven para conseguir compilar o código
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

#Copia os dados do código para dentro de um container
COPY . .

#Comando que compila o código em .jar para dentro da nuvem
RUN mvn clean package -DskipTests

#Baixando uma imagem mais leve sem o maven
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

#Copia o .jar gerado com o maven para um novo container limpo
COPY --from=build /app/target/*.jar app.jar

#Expõe a porta padrão do Spring Boot
EXPOSE 8080

#Comando que passa os arquivos para ligar a API
ENTRYPOINT ["java", "-jar", "app.jar"]