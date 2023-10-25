scalaVersion := "3.3.1"

lazy val contra =
  project
    .settings(
      scalaVersion := "3.3.1",
      scalacOptions ++= Seq("-Ykind-projector", "-explain")
    )
