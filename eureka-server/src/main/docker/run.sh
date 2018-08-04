#!/bin/sh

ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

echo "-------------------------------------------------------"
echo "         -[  Starting the Eureka Server    ]-          "
echo "-------------------------------------------------------"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/eureka_server/@project.build.finalName@.jar
