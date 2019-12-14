package dExamplets.cObjectExpressions

import java.awt.event.FocusEvent
import java.awt.event.FocusListener

fun main(args: Array<String>) {

    val r = object : FocusListener {
        override fun focusLost(e: FocusEvent?) {
            println("focus lost")
        }

        override fun focusGained(e: FocusEvent?) {
            println("focus gained")
        }
    }



    r.focusGained(null)
    r.focusLost(null)


}
    