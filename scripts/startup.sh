#!/bin/bash

set -ve

curl -s "https://storage.googleapis.com/signals-agents/logging/google-fluentd-install.sh" | bash
service google-fluentd restart &

apt-get install apt-transport-https ca-certificates

export GCSFUSE_REPO=gcsfuse-`lsb_release -c -s`
export DOCKER_USER=$(curl http://metadata.google.internal/computeMetadata/v1/instance/attributes/docker_user -H "Metadata-Flavor: Google")
export DOCKER_PASS=$(curl http://metadata.google.internal/computeMetadata/v1/instance/attributes/docker_pass -H "Metadata-Flavor: Google")

echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" | sudo tee /etc/apt/sources.list.d/gcsfuse.list
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" | sudo tee /etc/apt/sources.list.d/docker.list
apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D

apt-get update
apt-get install -yq linux-image-extra-$(uname -r) linux-image-extra-virtual docker-engine gcsfuse

systemctl start docker
systemctl enable docker

docker login --username=$DOCKER_USER --password=$DOCKER_PASS

### DEPLOY

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
