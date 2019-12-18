package b2BlackjackCli

import b1BlackjackMutable.Game
import b1BlackjackMutable.Hand
import jline.console.ConsoleReader

fun main(args: Array<String>) {
    val g = Game()
    val r = ConsoleReader()
    myLoop@ while (true) {
        r.clearScreen()
        gameView(g, r)
        r.print("Enter key d, h, s or q: ")
        r.flush()
        val c = r.readCharacter('d', 'h', 's', 'q').toChar()
        when (c) {
            'd' -> g.deal()
            'h' -> g.hit()
            's' -> g.stay()
            'q' -> break@myLoop
        }
    }
    r.clearScreen()
    r.flush()
}

fun gameView(g: Game, r: ConsoleReader) {
    r.println("*** BlackJack ***")
    handView(g.ph, r)
    handView(g.dh, r)
}

fun handView(h: Hand, r: ConsoleReader) {
    r.println("${h.name} Hand")
    h.cards.forEach {
        r.println("  ${it.name}")
    }
    r.println("  ${h.points} Points")
    repeat(7 - h.size, { r.println() })
}
