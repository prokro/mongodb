#!/bin/bash

set -euxo pipefail

sleep 1

docker exec -it simple-rs-node1 sh -c "mongo localhost:50051 /root/config.js"

sleep 1

# /etc/hots has line `127.0.0.1 simple-rs-node1 simple-rs-node2 simple-rs-node3`
mongo --host simple-rs/simple-rs-node1:50051,simple-rs-node2:50052,simple-rs-node3:50053 fill-mongo.js

mongo --host simple-rs-node1:50051 set-log-level.js
mongo --host simple-rs-node1:50052 set-log-level.js
mongo --host simple-rs-node1:50053 set-log-level.js


