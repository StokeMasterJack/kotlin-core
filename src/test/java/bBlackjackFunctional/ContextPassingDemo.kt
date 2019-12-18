package bBlackjackFunctional

class ContextPassingDemo {

    val myVar = 44

    fun passContextUsingReceiver() {
        Game().apply {
            Deck().apply {
                Hand(HandType.Player).apply {
                    Card(0).apply {

                        //explicit "this"
                        //  explicit "this" is simply the current "this", that is, the receiver passed into the current lambda.
                        //  It does not include any properties from parent's "this"

                        //explicit "this" locks you into the current "this", Card in this case:
                        println(this.suitName)  //explicit "this" is of type Card

                        //implicit "this" (i.e. omitting the keyword "this")
                        //  The implicit "this" is a merged "this". That is, a merging of the current "this" with
                        //  all parent this's into into a single mega (implicit) "this":

                        println(myVar)            //ContextPassingDemo
                        println(isGameActive)     //Game
                        println(isShuffled)       //Deck
                        println(type)             //Hand
                        println(suitName)         //Card

                        //If there is property name conflict, the nearest ancestor wins
                        //For example, both Deck and Hand have a size property buy Hand because
                        //it is nearer than Deck in the higherarchy
                        println(size)            //Hand.size

                        //@ qualifiers help a little bit
                        println(this@ContextPassingDemo.myVar)
                        println(this@apply)

                        //But @ qualifiers are not so useful since, in this case, "apply" is used all the way up the hierarchy
                        //the nearest this@apply ancestor wins which, in this case, is simply Card
                        //Thus, in this case, this@apply is the same as unqualified explicit "this"
                        //These two expressions refer to the same exact object (the Card)
                        println(this@apply)
                        println(this)

                        //@DslMarker
                        //You can use the @DslMarker annotation to disable parent merging,
                        // thus causing the implicit "this" to behave exactly the same as the explicit "this".


                    }
                }
            }
        }

    }

    fun passContextUsingArgIt() {
        Game().let {
            Deck().let {
                Hand(HandType.Player).let {
                    Card(0).let {
                        //can only access the most proximate "it" (the Card on this case)
                        println(it.valueName)
                        println(it.suitName)
                    }
                }
            }
        }

    }

    //With named args, you can access the most proximate arg or any parent arg
    fun passContextUsingArgNamed() {
        Game().let { g ->
            Deck().let { d ->
                Hand(HandType.Player).let { h ->
                    Card(0).let { c ->

                        println(g.isGameActive)       //Game
                        println(d.isShuffled)         //Deck
                        println(h.type)               //Hand
                        println(c.suitName)           //Card

                        //property name conflicts no longer a problem
                        println(d.size)              //Deck
                        println(h.size)              //Hand
                        println(h.points)            //Hand
                        println(c.points)            //Card
                    }
                }
            }
        }

    }
}

fun main(args: Array<String>) {
    ContextPassingDemo().apply {
        passContextUsingReceiver()
        passContextUsingArgIt()
        passContextUsingArgNamed()
    }
}
