FROM openjdk:17-oracle

EXPOSE 8761

WORKDIR /app

COPY target/ms-eureka-server-0.0.1-SNAPSHOT.jar /app/ms-eureka.jar

RUN ls -l /app

CMD ["java", "-jar", "ms-eureka.jar"]

RUN echo $'\n'$'\n''* * * ms-eureka-server image generated with success * * *'$'\n'$'\n'


