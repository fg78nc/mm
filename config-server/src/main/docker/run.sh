#!/bin/sh

ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

echo "-----------------------------------------------------------------------------------------"
echo "         -[ Waiting for the Eureka server to start on port $EUREKASERVER_PORT ]-         "
echo "-----------------------------------------------------------------------------------------"
while ! `nc -z eurekaserver $EUREKASERVER_PORT`; do sleep 3; done
echo "-----------------------------------------------------------------------------------------"
echo "         -[  Eureka server to start on port $EUREKASERVER_PORT ]-                        "
echo "-----------------------------------------------------------------------------------------"


echo "-----------------------------------------------------------------------------------------"
echo "         -[ Launching Config Server with Eureka Endpoint:  $EUREKASERVER_URI   ]-        "
echo "-----------------------------------------------------------------------------------------"
java -Djava.security.egd=file:/dev/./urandom -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI -jar /usr/local/config_server/@project.build.finalName@.jar
