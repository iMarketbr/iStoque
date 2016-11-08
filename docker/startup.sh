#!/bin/bash
set -ve

ISTOQUE_PROPERTIES=/opt/application.properties

cd $ISTOQUE_HOME

SPRING_CONFIG="--spring.config.location=file://$ISTOQUE_PROPERTIES"

exec java -jar build/libs/app.jar $SPRING_CONFIG
