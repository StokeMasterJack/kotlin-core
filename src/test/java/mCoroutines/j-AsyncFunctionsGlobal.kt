package mCoroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    fun aaAsync() = GlobalScope.async {
        delay(1000)
        if (true) throw RuntimeException()
        "AAA"
    }

    fun bbAsync() = GlobalScope.async {
        delay(1000)
        "BBB"
    }

    runBlocking {
        val resultA: Deferred<String> = aaAsync()
        val resultB: Deferred<String> = bbAsync()
        println("resultA = ${resultA.await()}")
        println("resultB = ${resultB.await()}")
    }


}
