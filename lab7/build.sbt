name := "lab6"

version := "0.1"

scalaVersion := "2.12.8"

val akkaHttpVersion =  "10.1.1"
val akkaVersion = "2.5.9"
val json4sVersion = "3.5.4"
lazy val alpakkaVersion = "0.18"
lazy val slickVersion = "3.2.1"
lazy val mysqlVersion = "5.1.34"


resolvers += "spray repo" at "http://repo.spray.io"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(

  "com.typesafe.slick" %% "slick" % "3.2.3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "mysql" % "mysql-connector-java" % mysqlVersion,
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "org.json4s" %% "json4s-core" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "org.json4s" %% "json4s-native" % json4sVersion,
  "de.heikoseeberger" %% "akka-http-json4s" % "1.20.1",
  "com.typesafe" % "config" % "1.3.3",
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % alpakkaVersion,
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "org.postgresql" % "postgresql" % "42.2.5",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
  "com.zaxxer" % "HikariCP" % "3.3.1",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0"
)