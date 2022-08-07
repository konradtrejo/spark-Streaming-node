name := "spark-Streaming-node"

version := "0.1"
val log4jVersion = "2.4.1"
scalaVersion := "2.11.12"
val sparkVersion = "2.4.8"
val postgresVersion = "42.2.2"
resolvers ++= Seq(
  "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven",
  "Typesafe Simple Repository" at "https://repo.typesafe.com/typesafe/simple/maven-releases",
  "MavenRepository" at "https://mvnrepository.com"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  // streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,

  // streaming-kafka
  //"org.apache.spark" % "spark-sql-kafka-0-10_2.12" % sparkVersion,

  // low-level integrations
  //"org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  //"org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion,



  // postgres
  "org.postgresql" % "postgresql" % postgresVersion,

  // logging
  "org.apache.logging.log4j" % "log4j-api" % log4jVersion,
  "org.apache.logging.log4j" % "log4j-core" % log4jVersion

)