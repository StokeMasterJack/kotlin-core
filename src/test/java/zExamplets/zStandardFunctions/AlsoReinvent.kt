package zExamplets.zStandardFunctions

import java.awt.Color
import javax.swing.JFrame

fun main(args: Array<String>) {

    val x: JFrame = JFrame().daveAlso {
        it.background = Color.RED
        it.setSize(300, 300)
        it.title = "MyFrame"
        it.isVisible = true
        it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    println(x::class)
}


fun <T> T.daveAlso(block: (arg: T) -> Unit): T {
    block(this)
    return this
}