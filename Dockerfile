# Первый уровень
FROM openjdk:17-slim as build
MAINTAINER "Spirin Danil <danilkaspirin@gmail.com>"

# Передаем в параметре запуска переменную указывающую на файл приложения
ARG JAR_FILE

# Добавляет файлы в контейнер
COPY ${JAR_FILE} app.jar

# Распаковывает файл jar в папку target/dependency
# Параметр mkdir -p: Не вызывать ошибок если существует; Создает родительские каталоги
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# Уровень два
# Та же среда выполнения
FROM openjdk:17-slim
# Скопировать распакованное приложение в новый контейнер
# (Копирует отдельные слои(см многослойные файлы JAR) из первого образа с именем build)
VOLUME /tmp

ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Запускаем приложение
ENTRYPOINT ["java", "-cp", "app:app/lib/*","ru.danilspirin.license.LicensingServiceApplication"]
