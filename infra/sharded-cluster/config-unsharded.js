var config = {
    "_id": "rs-unsharded",
    "members": [
        {
            "_id": 0,
            "host": "mongo1:27017"
        },
        {
            "_id": 1,
            "host": "mongo2:27017"
        },
        {
            "_id": 2,
            "host": "mongo3:27017"
        }
    ]
}

// > docker exec -it mongo1 mongo
// mongo> //paste above code
// mongo> rs.initiate(config)
// mongo> exit

// confirm other mongo nodes to be part of replicaset 
// > docker exec -it mongo2 mongo
// > docker exec -it mongo3 mongo