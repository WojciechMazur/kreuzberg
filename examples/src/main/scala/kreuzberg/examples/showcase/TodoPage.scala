package kreuzberg.examples.showcase

import kreuzberg._
import scalatags.Text.all.*

case class TodoPage(model: Model[TodoList]) extends ComponentBase {
  override def assemble: AssemblyResult = {
    for {
      value  <- subscribe(model)
      shower <- namedChild("shower", TodoShower(value))
      adder  <- namedChild("adder", TodoAdder(model))
    } yield {
      Assembly(
        div(),
        Vector(shower, adder)
      )
    }
  }
}