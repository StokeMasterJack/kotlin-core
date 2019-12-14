package qExamplets.dGenerics

open class Shape

class Circle(val radius: Int) : Shape()
class Square(val side: Int) : Shape()

fun main(args: Array<String>) {
    val c = Circle(10)
    val s = Square(5)
    val pr = Pr(c, s)
    println(pr.s1.radius)
    println(pr.s2.side)
}

class Pr<out T1 : Shape, out T2 : Shape>(val s1: T1, val s2: T2)

fun main2(args: Array<String>) {
//    val p = Pr<Circle, Square>
}
    