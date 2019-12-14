package mCoroutines

import kotlinx.coroutines.*

//functions that suspend must be marked Suspend
fun main(args: Array<String>) {

    runBlocking {

        coroutineScope {
            launch {
                aa1()
            }
            launch {
                aa2()
            }
        }
        println("aa - after")

        coroutineScope {
            launch {
                bb1()
            }
            launch {
                bb2()
            }
        }

        println("bb - after")

    }

    println("runBlocking - after")
}

private suspend fun bb2() {
    delay(1000L)
    println("bb2")
}

private suspend fun bb1() {
    delay(1000L)
    println("bb1")
}

private suspend fun aa2() {
    delay(1000L)
    println("aa2")
}

private suspend fun aa1() {
    delay(1000L)
    println("aa1")
}