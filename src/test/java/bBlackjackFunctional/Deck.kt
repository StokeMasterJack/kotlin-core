package bBlackjackFunctional

typealias Take = Pair<Deck, List<Card>>

fun mkCards(shuff: Boolean): List<Card> {
    return List(52, ::Card).transformIf(shuff) { it.shuffled() }
}

fun mkCards2(shuff: Boolean): List<Card> {
    return List(52, ::Card).transformIf2(shuff) { shuffled() }
}

//i = nextCard
class Deck private constructor(private val i: Int, private val shuffle: Boolean, private val all: List<Card>) {

//    constructor(shuffle: Boolean = true) : this(
//        i = 0,
//        shuffle = shuffle,
//        all = List(52, ::Card).transformIfArg(shuffle) { shuffled() })

    constructor(shuffle: Boolean = true) : this(
        i = 0,
        shuffle = shuffle,
        all = List(52, ::Card).shuffleIf(shuffle)
    )

    val size: Int get() = all.size - i

    val isEmpty: Boolean get() = i > 51
    val isVirgin: Boolean get() = i == 0

    private fun cp(
        i: Int,
        all: List<Card> = this.all
    ) = Deck(i = i, shuffle = this.shuffle, all = all)

    private fun use(n: Int): Deck = cp(i = this.i + n)
    private fun last(n: Int): List<Card> = all.subList(i, i + n)

    fun takeCards(n: Int = 1): Take = Take(use(n), last(n))

    val cards: List<Card> get() = if (isEmpty) emptyList() else all.subList(i, 52)

    fun dump() {
        cards.dump()
    }

    val isShuffled: Boolean get() = shuffle

    fun shuffled(): Deck = cp(i = 0, all = all.shuffleIf(shuffle))
    fun shuffledIfRunningLow(min: Int = 20): Deck = if (size < min) shuffled() else this


}

