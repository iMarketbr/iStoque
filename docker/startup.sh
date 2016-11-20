#!/bin/bash
set -ve

# Cloud SQL
SQL_DIR=/opt/cloudsql
SQL_INSTANCE=imarket-2016:us-east1:imarket-db
mkdir -p $SQL_DIR
wget https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -O $SQL_DIR/cloud_sql_proxy
chmod +x $SQL_DIR/cloud_sql_proxy
$SQL_DIR/cloud_sql_proxy -instances=$SQL_INSTANCE=tcp:3306 &

git clone https://github.com/iMarketbr/iStoque.git $ISTOQUE_HOME
ISTOQUE_PROPERTIES=/opt/application.properties

cd $ISTOQUE_HOME
./gradlew build

SPRING_CONFIG="--spring.config.location=file://$ISTOQUE_PROPERTIES"
exec java -jar $ISTOQUE_HOME/build/libs/istoque.jar $SPRING_CONFIG