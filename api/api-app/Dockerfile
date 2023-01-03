FROM bellsoft/liberica-openjdk-alpine:17

COPY ./api/api-app/build/libs/api-app-0.0.1-SNAPSHOT.jar /api-app.jar

EXPOSE 8085

VOLUME /logs

ENV ENVIRONMENT production

ENV LOG_PATH /logs

ENTRYPOINT ["java","-jar","/api-app.jar"]
