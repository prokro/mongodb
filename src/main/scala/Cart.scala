import java.util.{Date, UUID}

import com.mongodb.{BasicDBObject, DBObject}

case class Cart(id: UUID = UUID.randomUUID(), product: String, createdAt: Date = new Date())

object Cart {
  def toDbObject(cart: Cart): DBObject = {
    val dbObj = new BasicDBObject
    dbObj.put("_id", cart.id)
    dbObj.put("createdAt", cart.createdAt)
    dbObj.put("product", cart.product)
    dbObj
  }

  def fromDbObject(cartObj: DBObject): Cart = {
    Cart(
      id = cartObj.get("_id").asInstanceOf[UUID],
      product = cartObj.get("product").asInstanceOf[String],
      createdAt = cartObj.get("createdAt").asInstanceOf[Date]
    )
  }
}
