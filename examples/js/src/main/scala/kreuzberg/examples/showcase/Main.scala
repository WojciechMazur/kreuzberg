package kreuzberg.examples.showcase

import kreuzberg.engine.naive.Binder

object Main {
  def main(args: Array[String]): Unit = {
    import kreuzberg._
    val app = App()
    Logger.enableDebug()
    Binder.runOnLoaded(app, "root")
  }
}
