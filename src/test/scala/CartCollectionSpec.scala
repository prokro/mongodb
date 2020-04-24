import com.mongodb.DBObject
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import org.mongodb.scala.model.Filters
import org.mongodb.scala.{MongoClient, Observable}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CartCollectionSpec extends AnyFlatSpec with Matchers {

  val prefix = s"test ${Util.nowFormatted()}"

  "Cart collection" should "return what was stored" in {
    val mongos: MongoClient = MongoClient("mongodb://localhost:50044")
    val commerceDb = mongos.getDatabase("commerce")
    val cartsCol = commerceDb.getCollection[DBObject]("carts")

    val cart = Cart(product = s"$prefix product to be found")
    val anotherCart = Cart(product = s"$prefix product not to be found")
    execMongo(cartsCol.insertOne(Cart.toDbObject(cart)))
    execMongo(cartsCol.insertOne(Cart.toDbObject(anotherCart)))

    val results = execMongo(cartsCol.find(Filters.eq("_id", cart.id)))
    results should have size (1)

    val result: DBObject = results.head
    val cartFromDb = Cart.fromDbObject(result)
    cart should be(cartFromDb)
  }

  def execMongo[T](obs: Observable[T]): Seq[T] = {
    Task.fromFuture(obs.toFuture).runSyncUnsafe()
  }

}
