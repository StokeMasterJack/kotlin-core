package zExamplets.zStandardFunctions

fun main(args: Array<String>) {
    daveRepeat(3) {
        println(it)
    }

}

fun daveRepeat(times: Int, block: (index: Int) -> Unit) {
    for (i in 0 until times) {
        block(i)
    }
}