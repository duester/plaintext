import mill._
import mill.scalalib._

object plaintext extends ScalaModule {
    def scalaVersion: T[String] = "3.7.1"

    override def ivyDeps: T[Agg[Dep]] = Agg(
        ivy"dev.zio::zio:2.1.20",
        ivy"com.lihaoyi::fastparse:3.1.1"
    )

    object test extends ScalaTests with TestModule.ZioTest {
        override def ivyDeps: T[Agg[Dep]] = Agg(
            ivy"dev.zio::zio-test:2.1.20"
        )
    }
}