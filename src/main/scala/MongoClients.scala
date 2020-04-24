import org.mongodb.scala.MongoClient

object MongoClients {
  val mongos: MongoClient = MongoClient("mongodb://localhost:50044")
  val mongoRs: MongoClient = MongoClient("mongodb://simple-rs-node1:50051,simple-rs-node2:50052,simple-rs-node3:50053/?replicaSet=simple-rs")
}
