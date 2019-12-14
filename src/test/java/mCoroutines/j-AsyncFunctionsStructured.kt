package mCoroutines

import kotlinx.coroutines.*

//this style is better for exception propagation
fun main(args: Array<String>) {

    fun CoroutineScope.aaAsync() = async {
        delay(1000)
        "AAA"
    }

    fun CoroutineScope.bbAsync() = async {
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
