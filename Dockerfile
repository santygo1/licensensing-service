FROM openjdk:17-slim as build
LABEL maintainer="Spirin Danil <danilkaspirin@gmail.com>"

# Определяем рабочую директорию в контейнере
WORKDIR application

# Путь к файлу JAR
ARG JAR_FILE=target/license-service.jar

# Добавляет файлы в контейнер
COPY ${JAR_FILE} application.jar

# Распаковывает файл jar
RUN java -Djarmode=layertools -jar application.jar extract

#Та же среда выполнения
FROM openjdk:17-slim
# Скопировать распакованное приложение в новый контейнер
# (Копирует отдельные слои(см многослойные файлы JAR) из первого образа с именем build)
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./

# Запускаем приложение
# Используем org.springframework.boot.loader .JarLauncher для запуска
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]