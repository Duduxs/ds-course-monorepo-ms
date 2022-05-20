FROM openjdk:17-oracle

EXPOSE 8765

ARG ENV
ARG CONFIG_SERVER_URL

RUN echo "PROFILE ACTIVE $ENV"

ENV ENV=$ENV
ENV CONFIG_SERVER_URL=$CONFIG_SERVER_URL

WORKDIR /app

COPY target/ms-oauth-0.0.1-SNAPSHOT.jar /app/ms-oauth.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-oauth.jar"]

RUN echo $'\n'$'\n''* * * ms-oauth image generated with success * * *'$'\n'$'\n'


