FROM openjdk:17-oracle

EXPOSE 8762

ARG ENV
ARG CONFIG_SERVER_URL

RUN echo "PROFILE ACTIVE $ENV"

ENV ENV=$ENV
ENV CONFIG_SERVER_URL=$CONFIG_SERVER_URL

WORKDIR /app

COPY target/ms-worker-0.0.1-SNAPSHOT.jar /app/ms-worker.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-worker.jar"]

RUN echo $'\n'$'\n''* * * ms-worker image generated with success * * *'$'\n'$'\n'


