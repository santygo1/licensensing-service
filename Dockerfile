FROM openjdk:17-slim as build
LABEL maintainer="Spirin Danil <danilkaspirin@gmail.com>"

# Выполнится с тем что передали в docker-maven-plugin
ARG JAR_FILE

# Добавляет файлы в контейнер
COPY ${JAR_FILE} app.jar

# Распаковывает файл jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#Та же среда выполнения
FROM openjdk:17-slim

VOLUME /tmp

# Скопировать распакованное приложение в новый контейнер
# (Копирует отдельные слои(см многослойные файлы JAR) из первого образа с именем build)
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Запускаем приложение
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "ru.danilspirin.license.LicensingServiceApplication"]