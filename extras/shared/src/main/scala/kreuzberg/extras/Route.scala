package kreuzberg.extras

import kreuzberg._
import kreuzberg.util.Stateful

/** A Route within [[SimpleRouter]] */
trait Route {

  /** Returns true if the route can handle a given path. */
  def canHandle(path: String): Boolean

  /** Returns a title for that path. */
  def title(path: String): String

  /** Assembles a node for a given path. */
  def node(id: ComponentId, path: String): Stateful[AssemblyState, TreeNode]
}

object Route {

  /** Simple Route without parameters. */
  case class SimpleRoute[T](
      path: String,
      title: String,
      component: T
  )(implicit assembler: Assembler[T])
      extends Route {
    override def canHandle(path: String): Boolean = {
      this.path == path
    }

    override def title(path: String): String = title

    override def node(id: ComponentId, path: String): Stateful[AssemblyState, TreeNode] = {
      assembler.assembleWithId(id, component)
    }
  }

  case class DependentRoute[T](
      fn: PartialFunction[String, T],
      titleFn: String => String
  )(implicit assembler: Assembler[T])
      extends Route {
    override def canHandle(path: String): Boolean = fn.isDefinedAt(path)

    override def title(path: String): String = titleFn(path)

    override def node(id: ComponentId, path: String): Stateful[AssemblyState, TreeNode] =
      assembler.assembleWithId(id, fn(path))
  }
}