#!/bin/bash
set -ve

APP_ISTOQUE=istoque
ISTOQUE_PROPERTIES=/opt/application.properties

cp -rfv /opt/bucket/$APP_ISTOQUE/prod/production.properties $ISTOQUE_PROPERTIES

if docker ps | awk -v app="APP_ISTOQUE" 'NR>1{  ($(NF) == APP_ISTOQUE )  }'; then 
    docker stop "$APP_ISTOQUE" && docker rm -f "$APP_ISTOQUE" 
fi

docker run --name $APP_ISTOQUE -d -p 9000:9000 -v $ISTOQUE_PROPERTIES:$ISTOQUE_PROPERTIES imarket/$APP_ISTOQUE