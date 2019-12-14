package qExamplets.gDsl

fun main(args: Array<String>) {
    val d = Div2()

    d.div {
        div {
            //            +"Doo"
        }
        div {
            //            +"Foo"
        }
        div {
            //            +"Zoo"
        }
    }

    println(d.toString())
}


fun indent(depth: Int) = buildString {
    repeat(depth) { append("    ") }
}

class Div2 {

    val tagName: String = this::class.simpleName!!.toLowerCase()

    val children = mutableListOf<Div2>()

    fun ser(depth: Int, a: Appendable) {
        val prefix = indent(depth)
        a.appendln("$prefix<$tagName>")
        for (child in children) {
            child.ser(depth + 1, a)
        }
        a.appendln("$prefix</$tagName>")
    }

    override fun toString(): String {
        val a = StringBuilder()
        ser(0, a)
        return a.toString()
    }

    //    override fun toString(): String {
//        return "<div>${}</div>"
//    }

    fun div(action: Div2.() -> Unit): Div2 {
        val dd = Div2()
        dd.action()
        children.add(dd)
        return dd
    }

}