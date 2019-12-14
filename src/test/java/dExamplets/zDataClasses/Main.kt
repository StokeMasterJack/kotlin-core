package dExamplets.zDataClasses

data class D1(val x: Int = 1, val y: Int = 1, val z: Int = 1)

fun main(args: Array<String>) {
    val d1 = D1()
    val (x, y, _) = d1
    println(x)

    val d2 = d1.copy()
    val d3 = d1.copy(x = 3)
    
}
    