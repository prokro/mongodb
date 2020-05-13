```
db.getCollection("carts").find({"_id" : JUUID("aaf4ddcd-3784-4b0c-b098-9629922202df")}).sort({"createdAt": 1}).explain()
db.getCollection("carts").find({"_id" : JUUID("aaf4ddcd-3784-4b0c-b098-9629922202df")}).explain()
db.getCollection("carts").find({}).sort({"createdAt": 1}).explain()
db.getCollection("carts").find({}).explain()
```