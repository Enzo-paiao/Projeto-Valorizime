#!/bin/bash
echo "Executando app.sh"
echo "Substituindo variaveis em application.properties"
sed -i "s/APP_HOST/${APP_HOST}/g" application.properties
sed -i "s/APP_PORT/${APP_PORT}/g" application.properties
sed -i "s/APP_DB/${APP_DB}/g" application.properties
sed -i "s/APP_USERNAME/${APP_USERNAME}/g" application.properties
sed -i "s/APP_PASSWORD/${APP_PASSWORD}/g" application.properties
echo "application.properties modificado com sucesso!"
java -jar app.jar