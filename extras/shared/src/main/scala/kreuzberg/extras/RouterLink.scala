package kreuzberg.extras

import scalatags.Text.all._
import kreuzberg._
import kreuzberg.imperative._
import kreuzberg.scalatags._

case class PlainLink(
    name: String,
    target: String
) extends SimpleComponentBase {
  val click = Event.JsEvent("click", true, true)

  override def assemble(implicit c: SimpleContext): Html = {
    a(name, href := target)
  }
}

case class RouterLink(
    target: String,
    name: String,
    deco: Boolean = false
) extends SimpleComponentBase {

  override def assemble(implicit c: SimpleContext): Html = {
    val state = provide[RoutingState]
    val link  = anonymousChild(PlainLink(name, target))
    add(
      from(link)(_.click).map(_ => target),
      EventSink.ModelChange(state.currentRoute, (_, _) => target)
    )
    if (deco) {
      span("[", link.wrap, "]")
    } else {
      span(link.wrap)
    }
  }
}