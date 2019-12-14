package qExamplets.fLambdasWithReceivers

fun main(args: Array<String>) {

    val s = buildString2 {
        append("aaa")
        append("bb")
        append("cc")
    }
    println(s)  //aaabbcc


}