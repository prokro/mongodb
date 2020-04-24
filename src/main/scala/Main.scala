import com.mongodb.DBObject
import monix.eval._
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Observable => MonixObservable}
import org.mongodb.scala.model.Sorts
import org.mongodb.scala.{Completed, MongoClient, Observable}

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  val client: MongoClient = MongoClients.mongos

  sync {
    for {
      database <- client.listDatabaseNames()
      _ = println(s"found database: $database")
    } yield ()
  }

  val commerceDb = client.getDatabase("commerce")
  val cartsCol = commerceDb.getCollection[DBObject]("carts")

  def insertCartTask(product: String): Task[Completed] = Task.deferFuture {
    cartsCol.insertOne(Cart.toDbObject(Cart(product = product))).toFuture
  }

  val nowFormatted = Util.nowFormatted()
  val source = MonixObservable.range(0, 1000)
  val processed = source.mapParallelUnordered(parallelism = 10) { i =>
    val product = s"product $nowFormatted $i"
    insertCartTask(product)
  }
  val result: Task[List[Completed]] = processed.toListL

  Util.time(result.runSyncUnsafe())

  import Sorts._

  sync {
    for {
      count <- cartsCol.countDocuments()
      _ = println(s"count: $count")

      cart <- cartsCol
        .find()
        .sort(orderBy(descending("createdAt")))
        .limit(1)
      _ = println(cart)
    } yield ()
  }

  private def sync(block: Observable[Unit]): Unit =
    Await.ready(block.toFuture, 1.seconds)

}
