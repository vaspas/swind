import sbtbuildinfo.BuildInfoKey
import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneOffset}
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoPlugin
import sbtbuildinfo.BuildInfoPlugin.autoImport._
import scala.sys.process._

object BuildInfo {
  def buildInfoConfiguration(enable: Boolean): Project => Project = { p =>
    if (enable) {
      p.enablePlugins(BuildInfoPlugin)
        .settings(
          buildInfoPackage := "org.swind",
          buildInfoKeys := Seq[BuildInfoKey](
            name,
            version,
            scalaVersion,
            sbtVersion,
            BuildInfoKey.action("time") {
              DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC).format(Instant.now())
            },
            BuildInfoKey.action("gitHash") {
              buildGitRevision()
            },
            BuildInfoKey.action("gitBranch") {
              buildGitBranch()
            },
            BuildInfoKey.action("gitDirty") {
              buildIsDirty()
            }
          ),
          buildInfoOptions += BuildInfoOption.ToMap
        )
    }
    else p.disablePlugins(BuildInfoPlugin)
  }

  def buildGitRevision() = {
    "gitHash"
    //("git rev-parse --short HEAD" !!).stripLineEnd
  }

  def buildGitBranch() = {
    "gitBranch"
    //("git rev-parse --abbrev-ref HEAD" !!).stripLineEnd
  }

  def buildIsDirty() = {
    "gitDirty"
    //("git status --porcelain" !!).nonEmpty
  }
}
