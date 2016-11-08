#!/bin/bash
set -ve

BUCKET_DIR=/tmp/bucket
ISTOQUE_PROPERTIES=/opt/application.properties

mkdir -p $BUCKET_DIR
gcsfuse configuration.imarketbr.com $BUCKET_DIR

cp $BUCKET_DIR/istoque/prod/production.properties $ISTOQUE_PROPERTIES
fusermount -u $BUCKET_DIR
rm -r $BUCKET_DIR

docker pull imarket/istoque:latest
app="istoque"
if docker ps | awk -v app="app" 'NR>1{  ($(NF) == app )  }'; then
  docker stop "$app" && docker rm -f "$app"
fi
docker run --name istoque -d \
            -v $ISTOQUE_PROPERTIES:$ISTOQUE_PROPERTIES \
            -p 80:9000 imarket/istoque
