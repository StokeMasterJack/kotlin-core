package dExamplets.aConstructors

fun main(args: Array<String>) {
    val p1 = Point1(1, 1, 1)
    val p2 = Point1()

    val p3 = Point2(2, 2, 2)
    val p4 = Point2()

    println(p1)
    println(p2)
    println(p3)
    println(p4)
}

class Point1(val x: Int, y: Int, z: Int) {

    val y: Int = y
    var z: Int

    init {
        this.z = z
    }

    constructor() : this(10, 10, 10) {
        println("So some other stuff")
    }

    constructor(x: Int) : this(x, 10, 10) {
        println("So some other stuff")
    }

}

data class Point2(val x: Int = 5, val y: Int = x + 3, val z: Int = 20) {


}