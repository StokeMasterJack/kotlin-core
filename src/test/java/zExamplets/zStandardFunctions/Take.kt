package zExamplets.zStandardFunctions

fun main(args: Array<String>) {

    val b = Box(1, 1, 1)

    val b1 = b.takeIf { it.x == 1 }
    val b2 = b.takeIf { it.x == 2 }
    val b3 = b.takeUnless { it.x == 1 }
    val b4 = b.takeUnless { it.x == 2 }

    println(b1)
    println(b2) //null
    println(b3) //null
    println(b4)


}

