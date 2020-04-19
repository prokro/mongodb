#!/bin/bash

set -euxo pipefail

docker-compose stop
docker-compose rm -f -v
docker-compose up --detach --force-recreate --remove-orphans

sleep 1

# /root/config.js has been put there using docker-compose
docker exec -it mongo-config-server-rs-node1 sh -c "mongo localhost:27019 /root/config.js"
docker exec -it mongo-shard1-rs-node1 sh -c "mongo localhost:27018 /root/config.js"
docker exec -it mongo-shard2-rs-node1 sh -c "mongo localhost:27018 /root/config.js"
docker exec -it mongo-shard3-rs-node1 sh -c "mongo localhost:27018 /root/config.js"

sleep 15
docker exec -it mongo-mongos sh -c "mongo localhost:27017 /root/config.js"

mongo localhost:50044 setup-sharded-col.js
#mongo localhost:50044 fill-sharded-col.js
