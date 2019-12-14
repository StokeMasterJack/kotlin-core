package mCoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//no obvious difference

fun main(args: Array<String>) {

    runBlocking {
        println(this is CoroutineScope)  //this = runBlocking's "CoroutineScope"

        // launch's a new coroutine IN runBlocking's CoroutineScope
        val job1 = launch {
            delay(1000L)
            println("aa")
        }
        val job2 = launch {
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