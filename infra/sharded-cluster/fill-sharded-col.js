var com = db.getSiblingDB("commerce");
for (var i = 0; i < 100; i++) {
    com.carts.insert({ "name": "cart-" + i })
}