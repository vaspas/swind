import sbt._

object Dependencies {
  lazy val loggingDeps = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
    "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.18"
 )

  lazy val configDeps = Seq(
    "com.typesafe" % "config" % "1.3.2",
    "com.github.pureconfig" %% "pureconfig" % "0.8.0"
  )

  lazy val jsonDeps = Seq(
    "io.spray" %% "spray-json" % "1.3.2"
    //"org.json4s" %% "json4s-native" % "3.6.0-M2"
  )

  lazy val akkaDeps = Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.19"
  )

  lazy val akkaHttpDeps = Seq(
    "com.typesafe.akka" %% "akka-http-core" % "10.1.6",
    "com.typesafe.akka" %% "akka-http" % "10.1.6",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.6"
  )

lazy val akkaStreamDeps = Seq(
    "com.typesafe.akka" %% "akka-stream" % "2.5.13")

  lazy val testDeps = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5",

    "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0",
    "org.scalacheck" %% "scalacheck" % "1.13.5",

    "com.typesafe.akka" %% "akka-testkit" % "2.5.11"
  )
}
