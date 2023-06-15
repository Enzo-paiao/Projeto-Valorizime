FROM openjdk:17-jdk-alpine
RUN apk add --no-cache --upgrade bash
ENV APP_HOST=
ENV APP_PORT=
ENV APP_DB=
ENV APP_USERNAME=
ENV APP_PASSWORD=
WORKDIR /usr/src
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY app.sh app.sh
COPY application.properties application.properties
RUN sed -i 's/\r$//' app.sh
RUN chmod 755 app.sh
EXPOSE 8080
ENTRYPOINT ["/bin/bash","/usr/src/app.sh"]