package mCoroutines

import kotlinx.coroutines.*

//passing scope as receiver
fun main(args: Array<String>) {

    runBlocking {

        coroutineScope {
            aa1Launch()
            aa2Launch()
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

private fun CoroutineScope.aa2Launch(): Job {
    return launch {
        aa2()
    }
}

private fun CoroutineScope.aa1Launch() {
    launch {
        aa1()
    }
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