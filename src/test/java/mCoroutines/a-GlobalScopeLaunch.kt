package mCoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//program ends before our 2 coroutines have a chance to start
fun main(args: Array<String>) {

    // launch's a new coroutine in the Global CoroutineScope
    GlobalScope.launch {
        delay(1000L)
        println("aa")
    }
    GlobalScope.launch {
        delay(1000L)
        println("bb")
    }

    println("main-end")
}