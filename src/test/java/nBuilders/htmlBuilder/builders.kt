@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package nBuilders.htmlBuilder

@DslMarker
annotation class DaveTagMarker

typealias F<NB> = (NB.() -> Unit)?

private fun <T> T.app(f: F<T>): T {
    if (f != null) f()
    return this
}

@DaveTagMarker
sealed class NodeBuilder<N : Node> {
    abstract fun mk(): N
}

sealed class ElBuilder<E : El> : NodeBuilder<E>()

typealias MkEl<E> = (nodes: List<Node>) -> E

sealed class FlowBuilder<E : El>(val mkEl: MkEl<E>) : ElBuilder<E>() {
    private val mutableNodes: MutableList<Node> = mutableListOf()
    protected fun add(n: Node) = mutableNodes.add(n).run { }

//    protected fun <N : Node, NB : NodeBuilder<N>> add(b: NB) = add(b.mk())

    fun span(f: F<SpanBuilder> = null) = add(H.span(f))

    fun text(text: String) = add(H.text(text))

    operator fun String.unaryPlus() = text(this)

    override fun mk(): E = mkEl(mutableNodes)
}

sealed class InlineBuilder<E : FlowEl>(mkEl: MkEl<E>) : FlowBuilder<E>(mkEl)

sealed class BlockBuilder<E : FlowEl>(mkEl: MkEl<E>) : FlowBuilder<E>(mkEl) {
    fun div(f: F<DivBuilder> = null) = add(H.div(f))
    fun h1(f: F<H1Builder> = null) = add(H.h1(f))
    fun br() = add(H.br())
}


class H1Builder : BlockBuilder<H1>(::H1)
class DivBuilder : BlockBuilder<Div>(::Div)
class HeadBuilder : BlockBuilder<Head>(::Head)
class BodyBuilder : BlockBuilder<Body>(::Body)
class SpanBuilder : InlineBuilder<Span>(::Span)
class HtmlBuilder : NodeBuilder<Html>() {

    private var h: Head? = null
    private var b: Body? = null

    fun head(f: F<HeadBuilder>) = run { h = H.head(f) }
    fun body(f: F<BodyBuilder>) = run { b = H.body(f) }

    override fun mk() = Html(h, b)

}


object HtmlBuilders {
    fun text(t: String): Text = Text(t)
    fun br(): Br = Br
    fun h1(f: F<H1Builder> = null): H1 = H1Builder().app(f).mk()
    fun div(f: F<DivBuilder> = null): Div = DivBuilder().app(f).mk()
    fun head(f: F<HeadBuilder> = null): Head = HeadBuilder().app(f).mk()
    fun body(f: F<BodyBuilder> = null): Body = BodyBuilder().app(f).mk()
    fun span(f: F<SpanBuilder> = null): Span = SpanBuilder().app(f).mk()
    fun html(f: F<HtmlBuilder> = null): Html = HtmlBuilder().app(f).mk()
}

private typealias H = HtmlBuilders


