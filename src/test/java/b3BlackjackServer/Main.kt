package b3BlackjackServer

import io.javalin.Javalin

fun main() {
    val app = Javalin.create().start(7000)

    app.get("/") {
        BlackjackHttp.handleRequest(it)
    }

}

