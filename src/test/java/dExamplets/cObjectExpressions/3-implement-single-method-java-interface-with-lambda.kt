package dExamplets.cObjectExpressions

fun main(args: Array<String>) {


    val r = Runnable {
        println("Runnable")
    }

    val t = Thread(r)
    t.start()

}
    