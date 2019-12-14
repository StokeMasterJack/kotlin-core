package zExamplets.zStandardFunctions

fun main(args: Array<String>) {

    val b = Box(1, 1, 1)

    val b1 = b.daveTakeIf { it.x == 1 }
    val b2 = b.daveTakeIf { it.x == 2 }
    val b3 = b.daveTakeUnless { it.x == 1 }
    val b4 = b.daveTakeUnless { it.x == 2 }

    println(b1)
    println(b2) //null
    println(b3) //null
    println(b4)

}

fun <T> T.daveTakeIf(block: (T) -> Boolean): T? {
    val result: Boolean = block(this)
    return if (result) this else null
}

fun <T> T.daveTakeUnless(block: (T) -> Boolean): T? {
    val result: Boolean = block(this)
    return if (result) null else this
}