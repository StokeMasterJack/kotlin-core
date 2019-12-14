package mCoroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.random.Random

data class Msg(val x: Int, val y: Int)

val channel = Channel<Msg>()

fun startProducing(): Job = GlobalScope.launch {
    while (true) {
        channel.send(Msg(Random.nextInt(52), Random.nextInt(52)))
        delay(1000)
    }
}

fun startConsuming(): Job = GlobalScope.launch {
    while (true) {
        val msg: Msg = channel.receive()
        println(msg)
    }
}

fun main1() = runBlocking {
    val pJob = startProducing()
    val cJob = startConsuming()
    pJob.join()
    cJob.join()
}

fun main(args: Array<String>) {

    runBlocking {

        launch {
            while (true) {
                channel.send(Msg(Random.nextInt(52), Random.nextInt(52)))
                delay(1000)
            }
        }

        launch {
            while (true) {
                val msg: Msg = channel.receive()
                println(msg)
            }
        }


    }

}