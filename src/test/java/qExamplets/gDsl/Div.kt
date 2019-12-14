package qExamplets.gDsl

import kotlin.reflect.KClass

fun sp(n: Int): String = buildString {
    repeat(n) { append("    ") }
}


fun html(action: Html.() -> Unit): Html {
    val d = Html()
    d.action()
    return d
}


/*
Node
    Text
    Element
        Html
        Div
        Span
        H1
 */

class Html : Element()
class Div : Element()
class Span : Element()
class H1 : Element()

sealed class Node {
    abstract fun ser(depth: Int, a: Appendable)
}

class Text(val text: String) : Node() {
    override fun ser(depth: Int, a: Appendable) {
        val t = sp(depth)
        a.appendln("$t$text")
    }
}

sealed class Element : Node() {

    private val nodes: MutableList<Node> = mutableListOf()

    private fun <T : Element> doElement1(cls: KClass<T>, action: T.() -> Unit): T {
        return cls.java.newInstance().also {
            action(it)
            nodes.add(it)
        }
    }

    private fun <T : Element> doElement(element: T, action: T.() -> Unit): T {
        element.action()
        nodes.add(element)
        return element
    }

    fun div(a: Div.() -> Unit) = doElement(Div(), a)
    fun span(a: Span.() -> Unit) = doElement(Span(), a)
    fun h1(a: H1.() -> Unit) = doElement(H1(), a)

    operator fun String.unaryPlus() {
        nodes.add(Text(this))
    }

    override fun ser(depth: Int, a: Appendable) {
        val t = sp(depth)
        a.appendln("$t<$tagName>")
        nodes.forEach { it.ser(depth + 1, a) }
        a.appendln("$t</$tagName>")
    }

    override fun toString(): String {
        val a = StringBuilder()
        ser(0, a)
        return a.toString()
    }

    val tagName: String get() = this::class.simpleName!!.toLowerCase()
}