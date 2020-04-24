name := "mongo-sharding"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0"
libraryDependencies += "io.monix" %% "monix" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % Test