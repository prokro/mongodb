import java.util.{Date, UUID}

import com.mongodb.{BasicDBObject, DBObject}

case class Cart(id: UUID = UUID.randomUUID(), product: String)

object Cart {
  def toDbObject(cart: Cart): DBObject = {
    val dbObj = new BasicDBObject
    dbObj.put("_id", cart.id)
    dbObj.put("createdAt", new Date())
    dbObj.put("product", cart.product)
    dbObj
  }
}
