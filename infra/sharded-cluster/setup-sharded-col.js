sh.enableSharding("commerce");
var comDb = db.getSiblingDB("commerce");
comDb.carts.ensureIndex({ _id: "hashed" });
sh.shardCollection("commerce.carts", { _id: "hashed" });