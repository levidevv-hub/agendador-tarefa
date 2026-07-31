FROM gradle:7.5-jdk17 as build
WORKDIR /app
COPY . .
run grandle build --no-daemon
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar  /app/agendador-tarefas.jar
EXPOSE 8081

CMD ["java", "-jar", "agendador-tarefas.jar"]