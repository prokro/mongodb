rs.initiate({
    _id: "rs-config-server",
    configsvr: true,
    members: [
        {
            _id: 0,
            host: "mongo-config-server-rs-node1:27019"
        },
        {
            _id: 1,
            host: "mongo-config-server-rs-node2:27019"
        },
        {
            _id: 2,
            host: "mongo-config-server-rs-node3:27019"
        }
    ]
})
