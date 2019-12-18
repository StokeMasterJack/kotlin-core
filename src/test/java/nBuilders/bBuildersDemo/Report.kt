package nBuilders.bBuildersDemo

import nBuilders.htmlBuilder.HtmlBuilders as H

data class Person(val firstName: String, val lastName: String)

object Report {
    val myProp = 33
    fun mk(people: List<Person>) = H.html {
        head {
            div()
        }
        body {
            h1 {
                +"People"
            }
            div {
                people.forEach {
                    div {
                        span { +it.firstName }
                        span { +it.lastName }
                    }
                    br()
                }
            }
        }
    }

}



