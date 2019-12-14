package dExamplets.zOptionalAndNamedArgs

fun main(args: Array<String>) {
    f1()
    f1(10)
    f1(10, 20)

    f1(a = 2, b = 3)
    f1(4, b = 3)
//    f1(a = 5, 3)   won't work
}

fun f1(a: Int = 2, b: Int = a + 10) {
    println("a = ${a}")
    println("b = ${b}")
    println()
}
    