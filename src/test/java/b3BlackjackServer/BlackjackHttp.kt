package b3BlackjackServer

import b1Blackjack.Game
import b1Blackjack.Hand
import io.javalin.http.Context
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

enum class Action {
    DEAL, HIT, STAY
}

object BlackjackHttp {

    fun handleRequest(ctx: Context) {
        handleRequest(ctx.req, ctx.res)
    }

    fun handleRequest(request: HttpServletRequest, response: HttpServletResponse) {
        val action = request.paramEnum("action", Action::class)
        println("action = ${action}")

        val g = getGameFromSession(request)
        if (action == null) {
            g.deal()
        } else when (action) {
            Action.DEAL -> g.deal()
            Action.HIT -> g.hit()
            Action.STAY -> g.stay()
        }
        val htmlText = appView(g)
        response.sendHtml(htmlText)
    }

    private fun getGameFromSession(request: HttpServletRequest): Game {
        val s = request.session
        println("s = ${s}")

        var g = s.getAttribute("game")
        println("g = ${g}")
        if (g == null) {
            println("Creating new Game in session")
            g = Game()
            s.setAttribute("game", g)
        } else {
            println("Using existing game in session")
        }

        return g as Game
    }

    private fun appView(g: Game): String {
        val a: Appendable = StringBuilder()
        a.appendHTML().div {
            form {
                action = "/"
                gameView(g)
            }
        }
        return a.toString()
    }

    private fun FlowContent.gameView(g: Game) {
        div {
            h1 { +"Black Jack" }
            buttonsView(g)
            div {
                style = "display:flex"
                handView(g.ph)
                handView(g.dh)
            }
        }
    }

    private fun FlowContent.buttonsView(g: Game) {
        div {
            button {
                //                disabled = g.over
                name = "action"
                value = "HIT"
                type = ButtonType.submit
                +"Hit"
            }
            button {
                //                disabled = g.over
                name = "action"
                value = "STAY"
                type = ButtonType.submit
                +"Stay"
            }
            button {
                //                disabled = !g.over
                name = "action"
                value = "DEAL"
                type = ButtonType.submit
                +"Deal"
            }
        }
    }

    private fun FlowContent.handView(h: Hand) {
        div {
            style = "background:cyan;margin:1rem;padding:1rem;width:10rem;height:10rem"
            div { b { +"${h.name} Hand" } }
            h.cards.forEach {
                div {
                    +it.name
                }
            }
            div { b { +"${h.points} Points" } }
        }
    }

}