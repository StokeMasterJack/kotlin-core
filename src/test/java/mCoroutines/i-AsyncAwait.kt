package mCoroutines

import kotlinx.coroutines.*


fun main(args: Array<String>) {

    suspend fun aa(): String {
        delay(1000)
        return "AAA"
    }

    suspend fun bb(): String {
        delay(1000)
        return "BBB"
    }

    runBlocking {

        val resultA: Deferred<String> = async { aa() }
        val resultB: Deferred<String> = async { bb() }
        println("resultA = ${resultA.await()}")
        println("resultB = ${resultB.await()}")
    }


}
