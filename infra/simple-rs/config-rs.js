rs.initiate({
    _id: "simple-rs",
    members: [
        {
            _id: 0,
            host: "simple-rs-node1:50051"
        },
        {
            _id: 1,
            host: "simple-rs-node2:50052"
        },
        {
            _id: 2,
            host: "simple-rs-node3:50053"
        }
    ]
})
