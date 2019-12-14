package qExamplets.gDsl

fun main(args: Array<String>) {
    val h = html {
        div {
            h1{
                +"Hello"
            }
            div {
                +"Doo"
            }
            span {
                +"Foo"
            }
            div {
                +"Zoo"
            }
        }
    }
    println(h.toString())
}