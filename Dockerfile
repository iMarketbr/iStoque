FROM openjdk:8

ENV ISTOQUE_HOME /opt/istoque

RUN mkdir -p /opt/app

COPY . /opt/app
WORKDIR /opt/app

RUN ./gradlew build

ENTRYPOINT /opt/app/docker/startup.sh

EXPOSE 9000