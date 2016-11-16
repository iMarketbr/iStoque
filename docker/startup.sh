#!/bin/bash
set -ve

git clone https://github.com/iMarketbr/iStoque.git $ISTOQUE_HOME
ISTOQUE_PROPERTIES=/opt/application.properties

cd $ISTOQUE_HOME
./gradlew build

SPRING_CONFIG="--spring.config.location=file://$ISTOQUE_PROPERTIES"
exec java -jar $ISTOQUE_HOME/build/libs/istoque.jar $SPRING_CONFIG