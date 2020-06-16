# build container
FROM        openjdk:8-jdk-alpine as BUILD
COPY        . /src
WORKDIR     /src
RUN         ./gradlew --no-daemon build

# runtime container
FROM        openjdk:8-jre-alpine

ENV         APPLICATION_USER ktor
RUN         adduser -D -g '' $APPLICATION_USER

RUN         mkdir /app
RUN         chown -R $APPLICATION_USER /app

USER        $APPLICATION_USER

COPY        --from=BUILD /src/build/libs/link-extractor.jar /app/link-extractor.jar
WORKDIR     /app

CMD  ["java", "-jar", "link-extractor.jar"]
