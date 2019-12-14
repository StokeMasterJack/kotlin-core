package dExamplets.cObjectExpressions


fun main(args: Array<String>) {

    val o1 = object {
        val x = 10
        val y = 10

        val area get() = x * y
    }


    println(o1.x)
    println(o1.y)
    println(o1.area)

}
    