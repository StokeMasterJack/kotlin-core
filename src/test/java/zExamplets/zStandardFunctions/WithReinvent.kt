package zExamplets.zStandardFunctions

fun main(args: Array<String>) {

    val b = Box(1, 1, 1)

    val sum: Int = daveWith(b) {
        x + y + z
    }

    println("sum = $sum")

}

fun <T1, T2> daveWith(param: T1, action: T1.() -> T2): T2 {
    return param.action()
}



