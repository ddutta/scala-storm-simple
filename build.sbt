name := "simple-scala-pipeline"

// If you comment this out, SBT 0.10 will default to Scala 2.8.1
scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
    "storm" % "storm" % "0.8.1",
    "org.slf4j" % "slf4j-log4j12" % "1.6.4"
)

resolvers ++= Seq(
    "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
    "clojars" at "http://clojars.org/repo/",
    "clojure-releases" at "http://build.clojure.org/releases"
)

javaOptions += "-XX:MaxPermSize=1024m"

javaOptions += "-Xmx1024m"

fork := true

ivyLoggingLevel := UpdateLogging.Full

logLevel := Level.Info
