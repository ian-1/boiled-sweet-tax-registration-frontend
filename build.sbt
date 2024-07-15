import uk.gov.hmrc.DefaultBuildSettings

val appName = "boiled-sweet-tax-registration-frontend"

ThisBuild / majorVersion := 0
ThisBuild / scalaVersion := "2.13.14"

lazy val microservice = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    organization := "agents.scala.upskill",
    PlayKeys.playDefaultPort := 8765,
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    scalacOptions += "-Wconf:src=routes/.*:s",
    pipelineStages := Seq(gzip),
  )
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(CodeCoverageSettings.settings: _*)

lazy val it = project
  .enablePlugins(PlayScala)
  .dependsOn(microservice % "test->test")
  .settings(DefaultBuildSettings.itSettings())
  .settings(libraryDependencies ++= AppDependencies.test)
