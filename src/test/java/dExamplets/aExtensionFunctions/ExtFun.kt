package dExamplets.aExtensionFunctions

import java.text.DecimalFormat

fun Double?.cur(): String {
    val f = DecimalFormat("$00.00")
    return if (this != null) f.format(this) else f.format(0)
}

fun main(args: Array<String>) {
    val d1 = 2345.6789
    val d2 = null
    println(d1.cur())
    println(d2.cur())
     
    
}
    