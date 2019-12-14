package zExamplets.zStandardFunctions

import java.awt.Color
import javax.swing.JFrame

fun main(args: Array<String>) {

    val x: JFrame = JFrame().apply {
        background = Color.RED
        setSize(300, 300)
        title = "MyFrame"
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    println(x::class)


}

