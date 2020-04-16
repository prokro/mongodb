rs.initiate({
    _id: "rs-shard2",
    members: [
        {
            _id: 0,
            host: "mongo-shard2-rs-node1:27018"
        },
        {
            _id: 1,
            host: "mongo-shard2-rs-node2:27018"
        },
        {
            _id: 2,
            host: "mongo-shard2-rs-node3:27018"
        }
    ]
})
