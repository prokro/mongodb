var comDb = db.getSiblingDB("commerce");
for (var i = 0; i < 100; i++) {
    comDb.carts.insert({ "name": "cart-" + i })
}
comDb.setLogLevel(2, "query");