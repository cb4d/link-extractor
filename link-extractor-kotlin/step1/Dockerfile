FROM        openjdk:8-jdk as BUILD
COPY        . /src
WORKDIR     /src
RUN         ./gradlew --no-daemon  shadowJar

FROM        openjdk:8-jre
COPY        --from=BUILD /src/build/libs/link-extractor-all.jar /bin/runner/run.jar
WORKDIR     /bin/runner
ENTRYPOINT  ["java", "-jar", "run.jar"]
