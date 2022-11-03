package kreuzberg.examples.showcase

import kreuzberg._
import scalatags.Text.all.*

object AboutVersionShower extends ComponentBase {
  override def assemble: AssemblyResult = span("1.1")
}

object AboutPage extends ComponentBase {

  override def assemble: AssemblyResult = {
    for {
      version <- anonymousChild(AboutVersionShower) // This is used for testing if we garbage collect anonymous children correctly
    } yield {
      Assembly(
        div("Hello World"),
        Vector(version)
      )
    }
  }
}
