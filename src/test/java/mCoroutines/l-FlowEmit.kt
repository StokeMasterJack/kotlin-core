package mCoroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun generateNumbers(): Flow<Int> {
    return flow {
        for (a in 1..20) {
            println("gen $a")
            emit(a)
        }
    }
}

fun main(args: Array<String>) {
    runBlocking {
        val sq1: Flow<Int> = generateNumbers()

        val sq2 = sq1.filter { it < 6 }.map { "$it $it" }

        sq2.collect {
            println("sq3 $it")
        }

    }


}