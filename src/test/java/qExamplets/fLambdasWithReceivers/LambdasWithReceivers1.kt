package qExamplets.fLambdasWithReceivers

fun main(args: Array<String>) {

    val s = buildString1 {
        it.append("aaa")
        it.append("bb")
        it.append("cc")
    }
    println(s)


}

fun buildString1(action: (b: StringBuilder) -> Unit): String {
    val sb = StringBuilder()
    action(sb)
    return sb.toString()
}


    