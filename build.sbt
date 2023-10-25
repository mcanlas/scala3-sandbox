scalaVersion := "3.3.1"

lazy val contra =
  project
    .settings(
      scalaVersion := "3.3.1",
      libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0",
      scalacOptions += "-Ykind-projector"
    )
