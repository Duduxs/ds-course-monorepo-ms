#!/bin/bash

echo $'\n'"Removing networks, images and containers that was previously created."$'\n'
echo $'\n'"I'll do not remove any volume, but if you want to remove then pass also -v flag in docker container rm command (4 databases)."$'\n'
echo $'\n'"if you didn't execute this script yet, then ignore this advice."

sleep 3

echo $'\n'"Removing project containers"$'\n'

docker ps -a -q -f name=ds-course-ms | xargs docker rm -f

echo $'\n'"Now project images"$'\n'

# shellcheck disable=SC2046
docker images -a -q -f reference='ds-course-ms**' | xargs docker rmi -f

echo $'\n'"Finally ds-course-ms-net network"$'\n'
# shellcheck disable=SC2046
docker network rm ds-course-ms_ds-course-ms-net

echo $'\n'$'\n'"DONE!"$'\n'$'\n'

echo $'\n'"Executing script to init docker instances [MAKE SURE YOU'VE JDK 17 INSTALLED ON YOUR COMPUTER, A LINUX TERMINAL AND DOCKER (BE SURE TO LOG IN DOCKERHUB, BECZ YOU'LL NEED TO PULL IMAGES FROM THERE)]"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-config-server jar"$'\n'
# shellcheck disable=SC2028
cd ms-config-server/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-config-server jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-eureka-server jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd ms-eureka-server/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-eureka-server jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-worker jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd ms-worker/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-worker jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-user jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd ms-user/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-user jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-payroll jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd ms-payroll/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-payroll jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-oauth jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd ms-oauth/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-oauth jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"Generating ms-api-gateway jar"$'\n'
# shellcheck disable=SC2028
cd ../ && cd api-gateway/ && ./mvnw clean package -Dmaven.test.skip=true && echo $'\n'"ms-api-gateway jar generated with success"

# shellcheck disable=SC2028
echo $'\n'"All jars generated"$'\n'
# shellcheck disable=SC2028
echo $'\n'"Executing service-registry and config-server containers"$'\n'

docker-compose up -d ds-course-ms-eureka-server
docker-compose up -d ds-course-ms-config-server

echo $'\n'"Waiting to ms-config-server and ms-eureka-server init"$'\n'

for i in {10..1}
do
   echo "Both will be ready in $i seconds"
   sleep 1
done

echo $'\n'"Now starting others containers"$'\n'

docker-compose up -d ds-course-ms-worker-db
docker-compose up -d ds-course-ms-user-db
docker-compose up -d ds-course-ms-api-gateway
docker-compose up -d --scale ds-course-ms-worker=3 ds-course-ms-worker
docker-compose up -d --scale ds-course-ms-user=2 ds-course-ms-user
docker-compose up -d ds-course-ms-payroll
docker-compose up -d ds-course-ms-oauth

# shellcheck disable=SC2028
echo $'\n'"Now exec 'docker ps' command ;)"$'\n'

