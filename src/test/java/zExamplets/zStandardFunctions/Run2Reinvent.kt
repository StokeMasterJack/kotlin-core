package zExamplets.zStandardFunctions

fun main(args: Array<String>) {

    val b = Box(
        x = 10,
        y = 10,
        z = Foo.daveRun2 {
            val a = 1
            val b = 1
            a + b + xx
        }
    )

    println(b)

}

fun <T, R> R.daveRun2(block: R.() -> T): T {
    return block()
}
    