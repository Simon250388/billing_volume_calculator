FROM gradle:jdk11-hotspot

COPY . /api-project
RUN cd /api-project
RUN gradle distZip --no-daemon
RUN unzip -o ./build/distributions/api-0.0.1-SNAPSHOT.zip

EXPOSE 8080

ENTRYPOINT ["./api-project/api-0.0.1-SNAPSHOT/bin/api"]
