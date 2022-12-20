package kreuzberg

/** An Event which can be triggered by some component. */
sealed trait Event[E] {
  def map[F](f: E => F): Event[F] = Event.MappedEvent(this, f)
}

object Event {

  /** A JavaScript event */
  case class JsEvent(
      name: String,
      preventDefault: Boolean = false,
      capture: Boolean = false
  ) extends Event[ScalaJsEvent]

  /** Maps event data. */
  case class MappedEvent[E, F](
      underlying: Event[E],
      mapFn: E => F
  ) extends Event[F]
}