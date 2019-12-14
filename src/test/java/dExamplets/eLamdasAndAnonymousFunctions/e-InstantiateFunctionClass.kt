package dExamplets.eLamdasAndAnonymousFunctions

import org.junit.Test

class F0 : () -> Unit {
    override operator fun invoke() {
        println(0)
    }
}

class F1 : (Int) -> Int {
    override operator fun invoke(x: Int): Int {
        return x * x
    }
}

class F2 : (Int, Int) -> Int {
    override operator fun invoke(x: Int, y: Int): Int {
        return x * y
    }
}

class FunctionClass {
    @Test
    fun test() {
        val f0 = F0()
        val f1 = F1()
        val f2 = F2()
        useFunction(f0, f1, f2)
    }
}
