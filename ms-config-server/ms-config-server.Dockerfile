FROM openjdk:17-oracle

EXPOSE 8888

ARG GITHUB_USER
ARG GITHUB_PASS
ARG SERVICE_REGISTRY_URL

ENV GITHUB_USER=$GITHUB_USER
ENV GITHUB_PASS=$GITHUB_PASS
ENV SERVICE_REGISTRY_URL=$SERVICE_REGISTRY_URL

WORKDIR /app

COPY target/ms-config-server-0.0.1-SNAPSHOT.jar /app/ms-config-server.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-config-server.jar"]

RUN echo $'\n'$'\n''* * * ms-config-server image generated with success * * *'$'\n'$'\n'


