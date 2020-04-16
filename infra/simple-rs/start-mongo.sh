#!/bin/bash

set -euxo pipefail

docker-compose stop
docker-compose rm -f -v
docker-compose up --force-recreate --remove-orphans
