FROM openjdk:17-oracle

EXPOSE 8763

ARG ENV
ARG CONFIG_SERVER_URL

RUN echo "PROFILE ACTIVE $ENV"

ENV ENV=$ENV
ENV CONFIG_SERVER_URL=$CONFIG_SERVER_URL

WORKDIR /app

COPY target/ms-user-0.0.1-SNAPSHOT.jar /app/ms-user.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-user.jar"]

RUN echo $'\n'$'\n''* * * ms-user image generated with success * * *'$'\n'$'\n'


