package dExamplets.cObjectExpressions



class Dave(val x: Int) {

    companion object {

//        val cols:Map<String,String>

        const val y = 10

        fun f2() {
            println("f2 - static method (sort of) $y")
        }

    }

    fun f1() {
        println("f1 - instance method $x")
    }
}

fun main(args: Array<String>) {
    val o1 = Dave(1)
    val o2 = Dave(2)
    o1.f1()
    o2.f1()
    Dave.f2()

}
    