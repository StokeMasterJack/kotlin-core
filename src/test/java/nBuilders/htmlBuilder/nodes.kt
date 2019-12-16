@file:Suppress("MemberVisibilityCanBePrivate")

package nBuilders.htmlBuilder

private val emptyNodeList = emptyList<Node>()

sealed class Node {
    abstract val nodes: List<Node>
    val isEmpty get() = nodes.isEmpty()
    val containsOnlyTextContent: Boolean get() = nodes.all { it is Text }
}

class Text(val text: String) : Node() {
    override val nodes = emptyNodeList
}

sealed class El : Node() {
    val tagName get() = this::class.simpleName!!.toLowerCase()
}

sealed class EmptyElement : El() {
    override val nodes = emptyNodeList
}

sealed class NonEmptyElement : El()

sealed class FlowEl(override val nodes: List<Node>) : NonEmptyElement()

class Div(n: List<Node>) : FlowEl(n)
class H1(n: List<Node>) : FlowEl(n)
class Span(n: List<Node>) : FlowEl(n)
class Head(n: List<Node>) : FlowEl(n)
class Body(n: List<Node>) : FlowEl(n)
class Html(val head: Head? = null, val body: Body? = null) : NonEmptyElement() {
    override val nodes: List<Node> get() = listOfNotNull(head, body)
}

object Br : EmptyElement()