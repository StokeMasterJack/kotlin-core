package dExamplets.eLamdasAndAnonymousFunctions

import org.junit.Test

class CallableReference {
    @Test
    fun test1() {

        fun ff0() {
            println(0)
        }

        fun ff1(x: Int): Int {
            return x * x
        }

        fun ff2(x: Int, y: Int): Int {
            return x * y
        }

        val f0 = ::ff0
        val f1 = ::ff1
        val f2 = ::ff2


        useFunction(f0, f1, f2)
    }
}

