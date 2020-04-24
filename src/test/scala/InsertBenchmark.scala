import com.mongodb.DBObject
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.reactive.{Observable => MonixObservable}
import org.mongodb.scala.{Completed, MongoClient, MongoCollection}
import org.scalameter._

object InsertBenchmark extends Bench.LocalTime {

  val sizes: Gen[Int] = Gen.range(
    "number inserted carts"
  )(100, 500, 100)

  val cartsCol: MongoCollection[DBObject] = {
    val client: MongoClient = MongoClients.mongos
    val commerceDb = client.getDatabase("commerce")
    commerceDb.getCollection[DBObject]("carts")
  }

  // multiple tests can be specified here
  performance of "Sharded cluster" in {
    measure method "insert cart" in {

      using(sizes) in { size =>
        def insertCartTask(product: String): Task[Completed] = Task.deferFuture {
          cartsCol.insertOne(Cart.toDbObject(Cart(product = product))).toFuture
        }

        val nowFormatted = Util.nowFormatted()
        val source = MonixObservable.range(0, size)
        val processed = source.mapParallelUnordered(parallelism = 10) { i =>
          val product = s"product $nowFormatted $i"
          insertCartTask(product)
        }
        val result: Task[List[Completed]] = processed.toListL
        result.runSyncUnsafe()
      }
    }
  }
}
