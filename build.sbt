import sbt._
import Configurations._
import Dependencies._

lazy val root = project.in(file(".")).
  configure(defaultSettings()).
    aggregate(
      rollserver)

lazy val rollserver = project.in(file("rollserver")).
  configure(defaultSettings(target = true)).
  enablePlugins(JavaServerAppPackaging).
  settings(
    libraryDependencies ++= akkaDeps++
        akkaHttpDeps++
        configDeps++
        akkaStreamDeps++
        testDeps++
        loggingDeps,
    name := "rollserver",
    mainClass := Some("swind.rollserver.Main")
  )