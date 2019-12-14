package zExamplets.zStandardFunctions

import java.awt.Color
import javax.swing.JFrame
import javax.swing.WindowConstants.EXIT_ON_CLOSE

fun main(args: Array<String>) {

    val f = JFrame().daveApply {
        title = "Dave"
        background = Color.RED
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(200, 200)
    }

}

fun <T> T.daveApply(block: T.() -> Unit): T {
    block()
    return this
}

/*
@kotlin.internal.InlineOnly
public inline fun <T> T.apply(block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block()
    return this
}
 */