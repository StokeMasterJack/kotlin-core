package rConfigurator

fun main(args: Array<String>) {

    val csp = mkCsp {

        val a = +"a"
        val b = +"b"
        val c = +"c"
        val d = +"d"
        val e = +"e"
        val f = +"f"
        val g = +"g"
        val h = +"h"
        val i = +"i"

        conflict(a, b)
        conflict(a, c)
        requires(a, d)
        requires(d, or(e, f))
        requires(f, and(g, h, a))

        val csp = mk()

        csp.print()

        csp.maybeSimplify(a, b).print()
        csp.maybeSimplify(a, i, e).print()
        csp.maybeSimplify(f).print()


    }
}


