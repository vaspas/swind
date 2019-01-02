import sbt.Keys.name
import sbt._
import sbtassembly.AssemblyPlugin
import sbtassembly.AssemblyPlugin.autoImport._

object Assembly {

  def assemblyConfiguration(enable : Boolean) : Project => Project = { p =>
    if(enable) {
      p.enablePlugins(AssemblyPlugin).
        settings (
          assemblyJarName in assembly := s"${name.value}-assembly.jar",
          assemblyOutputPath in assembly := file("target/out") / (assemblyJarName in assembly).value,
          assemblyMergeStrategy in assembly := {
            case PathList("META-INF", xs@_*) => MergeStrategy.discard
            case PathList(ps@_*) if ps endsWith ".conf" => MergeStrategy.concat
            case PathList(ps@_*) if ps endsWith ".class" => MergeStrategy.first
            case PathList("reference-overrides.conf") => MergeStrategy.concat
            case o =>
              val oldStrategy = (assemblyMergeStrategy in assembly).value
              oldStrategy(o)
          }
        )
    }
    else p.disablePlugins(AssemblyPlugin)
  }
}