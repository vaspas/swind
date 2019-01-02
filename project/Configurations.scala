import sbt.Keys._
import sbt._
import BuildInfo.buildInfoConfiguration
import Assembly.assemblyConfiguration

object Configurations {

  def defaultSettings(target: Boolean = false): Project => Project =
    assemblyConfiguration(enable = target) andThen
      buildInfoConfiguration(enable = target) andThen {
      _.settings(
        organization := "org.swind",
        scalaVersion := "2.11.12",

        scalacOptions ++= Seq(
          "-deprecation",
          "-encoding", "UTF-8",
          "-feature",
          "-language:existentials",
          "-language:higherKinds",
          "-language:implicitConversions",
          "-language:postfixOps",
          "-unchecked",
          "-Xfatal-warnings",
          "-Xfuture",
          "-Xlint",
          "-Yno-adapted-args",
          "-Ywarn-dead-code",
          "-Ywarn-unused-import",
          "-Ywarn-value-discard",
          "-Xexperimental",
          "-Ypartial-unification"
        ),

        javacOptions ++= Seq(
          "-encoding", "UTF-8"
        ),

        fork in run := true,
        concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)
      )
    }

  def integrationTestsConfiguration: Project => Project = {
    _.configs(IntegrationTest).
      settings(Defaults.itSettings: _*).
      settings(
        (libraryDependencies in IntegrationTest) ++= {
          (libraryDependencies in Test).value
        }
      )
  }
 }