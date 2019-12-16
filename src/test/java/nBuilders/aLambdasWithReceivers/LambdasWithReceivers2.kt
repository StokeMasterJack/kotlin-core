package nBuilders.aLambdasWithReceivers

fun main(args: Array<String>) {

    val s = buildString2 {
        append("aaa")
        append("bb")
        append("cc")
    }
    println(s)  //aaabbcc


}

fun buildString2(action: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.action()
    return sb.toString()
}


    