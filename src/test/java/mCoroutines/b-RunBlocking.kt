package mCoroutines.a

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//program STILL ends before our 2 coroutines have a chance to start
fun main(args: Array<String>) {
    //Runs a new coroutine and **blocks** the current thread _interruptibly_ until its completion
    runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("aa")
        }
        GlobalScope.launch {
            delay(1000L)
            println("bb")
        }
        println("runBlocking-end")
    }

    println("main-end")
}