package mCoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        val job1 = GlobalScope.launch {
            delay(1000L)
            println("aa")
        }
        val job2 = GlobalScope.launch {
            delay(1000L)
            println("bb")
        }

        println("runBlocking-beforeJoins")

        job1.join()
        job2.join()

        println("runBlocking-afterJoins")


    }


    println("main-end")
}