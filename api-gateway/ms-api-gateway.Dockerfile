FROM openjdk:17-oracle

EXPOSE 8776

ARG SERVICE_REGISTRY_URL
ARG CONFIG_SERVER_URL
ARG ENV

RUN echo "PROFILE ACTIVE $ENV"

ENV SERVICE_REGISTRY_URL=$SERVICE_REGISTRY_URL
ENV CONFIG_SERVER_URL=$CONFIG_SERVER_URL
ENV ENV=$ENV

WORKDIR /app

COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app/ms-api-gateway.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-api-gateway.jar"]

RUN echo $'\n'$'\n''* * * ms-api-gateway image generated with success * * *'$'\n'$'\n'


