package qExamplets.dGenerics

class Pair<A,out  B>(val a: A, val b: B)

fun main(args: Array<String>) {

    val pair1 = Pair(Rect(10, 10), Rect(2, 2))
    val pair2 = Pair(Point(1, 1), Point(3, 3))

    println(pair1.a.length)
    println(pair2.a.x)

}


    