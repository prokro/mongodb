#!/bin/bash

set -euxo pipefail

docker-compose stop
docker-compose rm -f -v
docker-compose up --force-recreate --remove-orphans --detach

sleep 1
docker exec -it simple-rs-node1 sh -c "mongo localhost:50051 /root/config.js"

