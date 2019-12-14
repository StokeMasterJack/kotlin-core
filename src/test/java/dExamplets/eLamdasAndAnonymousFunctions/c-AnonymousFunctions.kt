package dExamplets.eLamdasAndAnonymousFunctions

import org.junit.Test

class AnonymousFunctions {
    @Test
    fun test() {
        val f0 = fun() {
            println(0)
        }

        val f1 = fun(x: Int): Int {
            return x * x
        }

        val f2 = fun(x: Int, y: Int): Int {
            return x * y
        }

        useFunction(f0, f1, f2)
    }
}

