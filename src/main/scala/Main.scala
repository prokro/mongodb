import scala.concurrent.Await
import scala.concurrent.duration._

import org.mongodb.scala.{MongoClient, Observable}

object Main extends App {

  case class Cart(id: Any, name: String)

  val mongos: MongoClient = MongoClient("mongodb://localhost:50044")

  sync {
    for {
      database <- mongos.listDatabaseNames()
      _ = println(s"found database: $database")
    } yield ()
  }

  val commerceDb = mongos.getDatabase("commerce")
  val cartsCol = commerceDb.getCollection("carts")

  // todo make uuid work
  // val carts = (1 to 1000) map { i =>
  //   Document("_id" -> UUID.randomUUID(), "name" -> s"product $i")
  // }

  sync {
    for {
      count <- cartsCol.countDocuments()
      _ = println(s"count: $count")
      cart <- cartsCol.find()
      _ = println(cart)
    } yield ()
  }

  private def sync(block: Observable[Unit]): Unit =
    Await.ready(block.toFuture, 30.seconds)

}
