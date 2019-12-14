package zExamplets.zStandardFunctions

fun main(args: Array<String>) {

    val b = Box(
        x = 10,
        y = 10,
        z = daveRun1 {
            val a = 1
            val b = 1
            a + b
        }
    )

    println(b)

}

fun <T> daveRun1(block: () -> T): T {
    return block()
}
    