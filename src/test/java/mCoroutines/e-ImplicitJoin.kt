package mCoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Join not needed - outer scopes block until inner coroutines complete

fun main(args: Array<String>) {

    runBlocking {
        println(this is CoroutineScope)  //this = runBlocking's "CoroutineScope"

        // launch's a new coroutine IN runBlocking's CoroutineScope
        launch {
            delay(1000L)
            println("aa")
        }
        launch {
            delay(1000L)
            println("bb")
        }

    }

    println("main-end")
}