ThisBuild / scalaVersion := "2.13.12"

ThisBuild / fork := true

ThisBuild / javaOptions ++= Seq(
  "--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED"
)

lazy val root = (project in file("."))
  .settings(
    name := "kafka-spark-app",
    version := "0.1.0",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.5.1",
      "org.apache.spark" %% "spark-sql" % "3.5.1",
      "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.5.1"
    )
  )
