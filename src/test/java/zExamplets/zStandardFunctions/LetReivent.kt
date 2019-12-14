package zExamplets.zStandardFunctions

fun main(args: Array<String>) {
    val r = Box(
        x = 10,
        y = Foo.daveLet {
            val a = 1 + 2 + 3
            val b = 4 + 5 + 6
            val c = 7 + 8 + 9
            a + b + c + it.xx
        },
        z = 44
    )
    println(r)
}

fun <R, T> T.daveLet(block: (T) -> R): R {
    return block(this)
}


