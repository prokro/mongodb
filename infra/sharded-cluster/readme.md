### start

`> docker-compose stop`

`> docker-compose up --detach --force-recreate --remove-orphans`

`> docker exec -it mongo-sharded-1 mongo`

`mongo> //paste above code and execute`

`sh.status()`
`db.carts.getShardDistribution()`
`db.adminCommand({ listShards: 1 })`

```
// https://dzone.com/articles/composing-a-sharded-mongodb-on-docker
// https://docs.mongodb.com/manual/reference/method/js-sharding/
// READ https://www.slideshare.net/JasonTerpko/mongodb-sharded-cluster-tutorial-94682018
// READ http://learnmongodbthehardway.com/schema/sharding/
// READ https://docs.mongodb.com/manual/core/sharding-balancer-administration/#sharding-migration-thresholds
// READ https://docs.mongodb.com/manual/faq/sharding/
```

// mongo> exit

### replicaset status

* `rs.status()`

