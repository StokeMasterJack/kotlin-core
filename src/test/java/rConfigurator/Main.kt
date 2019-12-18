package rConfigurator

open class Vr() {

    val name: String get() = this::class.simpleName!!
    override fun toString(): String {
        return name
    }


}

object A : Vr()


fun main(args: Array<String>) {
    println(A)
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


