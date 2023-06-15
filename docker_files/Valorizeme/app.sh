#!/bin/bash
echo "Executando app.sh"
echo "Substituindo variaveis em application.properties"
echo $APP_AWS_SECRETKEY | sed -r 's/\//\\\//g' | sed -r 's/\+/\\\+/g' > AUX
export APP_AWS_SECRETKEY=`cat AUX`
rm -f AUX
sed -i "s/APP_AWS_ACCESSKEY/${APP_AWS_ACCESSKEY}/g" application.properties
sed -i "s/APP_AWS_SECRETKEY/${APP_AWS_SECRETKEY}/g" application.properties
echo "application.properties modificado com sucesso!"
java -jar app.jar