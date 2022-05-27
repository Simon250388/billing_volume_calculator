FROM gradle:7.3-jdk17-alpine

COPY --chown=gradle:gradle . /api-project
WORKDIR /api-project
RUN gradle bootJar --no-daemon

EXPOSE 8085
WORKDIR /api-project

CMD java -jar build/libs/api-0.0.1-SNAPSHOT.jar
