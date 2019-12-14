package zExamplets.zStandardFunctions

import java.awt.Color
import javax.swing.JFrame

/**
 * Same as apply except use it  instead of "this"
 */
fun main(args: Array<String>) {

    val x: JFrame = JFrame().also {
        it.background = Color.RED
        it.setSize(300, 300)
        it.title = "MyFrame"
        it.isVisible = true
        it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    println(x::class)

}

