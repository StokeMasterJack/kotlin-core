package zExamplets.zDelagatesProps

fun main(args: Array<String>) {
    val f = Rect(10, 10)
    println(1)
    println(f.area)
    println(f.area)
    println(f.area)

}

data class Rect(val length: Int, val width: Int) {

    val area by lazy {
        println("Computing area")
        length * width
    }

}
    