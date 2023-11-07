scalaVersion := "3.3.1"

lazy val contra =
  project
    .settings(
      scalaVersion := "3.3.1",
      scalacOptions ++= Seq("-Ykind-projector", "-explain")
    )

lazy val option =
  project
    .settings(
      scalaVersion := "2.13.10",
      libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"
    )
