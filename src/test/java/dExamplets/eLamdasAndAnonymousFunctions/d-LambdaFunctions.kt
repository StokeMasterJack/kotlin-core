package dExamplets.eLamdasAndAnonymousFunctions

import org.junit.Test

class LambdaFunctions{
    @Test
    fun test1() {

        val f0 = {
            println(0)
        }

        val f1a = { x: Int ->
            x * x
        }

        val f1b: IntToInt = {
            it * it
        }

        val f2 = { x: Int, y: Int ->
            x * y
        }

        useFunction(f0, f1a, f2)
        useFunction(f0, f1b, f2)
    }

    @Test
    fun test2() {
        fun do3Times(f:VF){
            f()
            f()
            f()
        }

        do3Times {
            println("yo")
        }
    }
}
