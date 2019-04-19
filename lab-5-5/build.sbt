name := "lab-5-5"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.21"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.8",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.8" % Test,
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "com.softwaremill.sttp" %% "core" % "1.5.11" % Test
)
