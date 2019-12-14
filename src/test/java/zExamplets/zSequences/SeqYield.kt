package zExamplets.zSequences

fun generateNumbers(): Sequence<Int> {
    return sequence {
        for (a in 1..20) {
            println("gen $a")
            yield(a)
        }
    }
}

fun main() {

    val sq1: Sequence<Int> = generateNumbers()

    val sq2 = sq1.filter { it < 6 }.map { "$it $it" }

    val sq3 = sq2.take(3)

    sq3.forEach {
        println("sq3 $it")
    }


}