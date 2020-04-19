import java.util.UUID

import com.mongodb.{BasicDBObject, DBObject}

import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.concurrent.duration._
import org.mongodb.scala.{MongoClient, Observable}

object Main extends App {

  val mongos: MongoClient = MongoClient("mongodb://localhost:50044")

  sync {
    for {
      database <- mongos.listDatabaseNames()
      _ = println(s"found database: $database")
    } yield ()
  }

  val commerceDb = mongos.getDatabase("commerce")
  val cartsCol = commerceDb.getCollection[DBObject]("carts")

  sync {
    // todo check why not always 1000 carts are inserted
    (1 to 1000) map { i =>
      cartsCol.insertOne {
        val dbObj = new BasicDBObject
        dbObj.put("_id", UUID.randomUUID())
        dbObj.put("name", s"product $i")
        dbObj
      }
    }
  }

  sync {
    for {
      count <- cartsCol.countDocuments()
      _ = println(s"count: $count")
      cart <- cartsCol.find()
      _ = println(cart)
    } yield ()
  }

  implicit val ec: ExecutionContextExecutor = scala.concurrent.ExecutionContext.global

  private def sync[T](block: => Seq[Observable[T]]): Unit =
    Await.ready(Future.sequence(block.map(_.toFuture)), 1.hour)

  private def sync(block: Observable[Unit]): Unit =
    Await.ready(block.toFuture, 30.seconds)

}
