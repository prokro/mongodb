name := "mongo-sharding"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0"
libraryDependencies += "io.monix" %% "monix" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % Test

libraryDependencies += "com.storm-enroute" %% "scalameter" % "0.19" % Test
testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
logBuffered := false
parallelExecution in Test := false