val scala3Version = "3.3.0"
val circeVersion = "0.14.1"
lazy val root = project
  .in(file("."))
  .settings(
    name := "ScalaPub",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0",
    libraryDependencies ++= Seq("circe-core", "circe-generic", "circe-parser").map("io.circe" %% _ % circeVersion),
    libraryDependencies += "com.apicatalog" % "titanium-json-ld" % "1.3.2",
    libraryDependencies += "org.glassfish" % "jakarta.json" % "2.0.1"
  )
