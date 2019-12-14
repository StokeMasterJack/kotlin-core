package qExamplets.dGenerics

val map = mapOf(
    33 to Point(1, 1),
    44 to Rect(10, 10)
)

fun <T> getFromMap(id: Int): T {
    val v = map[id] ?: throw IllegalArgumentException()
    return map as T
}

fun <T> foo(id: Int) {
    println(111)
}

fun main(args: Array<String>) {


    val r: Rect = getFromMap(33)
    val p: Point = getFromMap(44)

    println(r.length)
    println(p.x)


}





