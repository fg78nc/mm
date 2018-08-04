FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/eureka_server
ADD @project.build.finalName@.jar /usr/local/eureka_server/
ADD run.sh run.sh
RUN chmod +x run.sh
ENTRYPOINT ["sh", "run.sh"]