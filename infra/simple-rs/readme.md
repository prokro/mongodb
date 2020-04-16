### using mongo client

#### connected to secondary

* set `readPref` when querying:
  * `db.getCollection('carts').find({}).readPref("secondary")`
* OR set slaveOk before querying
  * `rs.slaveOk(false)`
  * `db.getCollection('carts').find({})`
* OR set `readPref` on connection before querying
  * `db.getMongo().setReadPref("secondary")`
  * `db.getCollection('carts').find({})`

#### connected to primary

nothing works, queries will always be executed on primary

#### connected to replica set

e.g. doing connecting like this:
 
`mongo --host <rs>/<node1>:<port1>,<node2>:<port2>,<node2>:<port3>`

* `db.getCollection('carts').find({}).readPref("secondary")`
  * will be executed on secondary
* `db.getCollection('carts').find({})`
  * will be executed on primary
* `db.getMongo().setReadPref("secondary")`
  * will execute all following queries on secondaries
* `rs.slaveOk(false)` WILL NOT make queries execute on secondaries

### using robo3t
 
#### robo3t is not a mongo client

* when connected to a primary or  
  * when connected to replica set
  * it will never route traffic to a secondary
* when connected to secondary
  * neither `slaveOk` or `readPref` need to be set
  * all queries will be executed on the secondary
