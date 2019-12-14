package mCoroutines

import kotlinx.coroutines.*


fun main(args: Array<String>) {

    runBlocking {

        coroutineScope {
            launch {
                delay(1000L)
                println("aa1")
            }
            launch {
                delay(1000L)
                println("aa2")
            }
        }
        println("aa - after")

        coroutineScope {
            launch {
                delay(1000L)
                println("bb1")
            }
            launch {
                delay(1000L)
                println("bb2")
            }
        }

        println("bb - after")

    }

    println("runBlocking - after")
}