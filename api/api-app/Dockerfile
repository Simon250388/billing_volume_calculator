FROM gradle:7.6-jdk17-alpine

COPY --chown=gradle:gradle . /api-project
WORKDIR /api-project
RUN gradle bootJar --nodaemon -x test

EXPOSE 8085
WORKDIR /api-project

VOLUME /logs

ENV ENVIRONMENT production
ENV LOG_PATH /logs

CMD java -jar api/api-app/build/libs/api-app-0.0.1-SNAPSHOT.jar

# docker run --volume /var/log/billing-api-log:/logs billing-api
