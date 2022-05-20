FROM openjdk:17-oracle

EXPOSE 8764

ARG ENV
ARG CONFIG_SERVER_URL

RUN echo "PROFILE ACTIVE $ENV"

ENV ENV=$ENV
ENV CONFIG_SERVER_URL=$CONFIG_SERVER_URL

WORKDIR /app

COPY target/ms-payroll-0.0.1-SNAPSHOT.jar /app/ms-payroll.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-payroll.jar"]

RUN echo $'\n'$'\n''* * * ms-payroll image generated with success * * *'$'\n'$'\n'


