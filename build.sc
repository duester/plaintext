//| mill-version: 1.0.3

import mill.*
import mill.scalalib.*
import mill.scalalib.publish.*

object plaintext extends ScalaModule, SonatypeCentralPublishModule {
  def scalaVersion = "3.7.1"

  def publishVersion = "0.0.2-SNAPSHOT"

  def repositories = Seq("https://central.sonatype.com/repository/maven-snapshots")

  def mvnDeps = Seq(
    mvn"dev.zio::zio:2.1.20",
    mvn"com.lihaoyi::fastparse:3.1.1"
  )

  object test extends ScalaTests, TestModule.ZioTest {
    def mvnDeps = Seq(
      mvn"dev.zio::zio-test:2.1.20"
    )
  }

  def pomSettings = PomSettings(
    description = "Simple plain text document model",
    organization = "io.github.duester",
    url = "https://github.com/duester/plaintext",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("duester", "plaintext"),
    developers =
      Seq(Developer("duester", "Maxim Duester", "https://github.com/duester"))
  )
}
