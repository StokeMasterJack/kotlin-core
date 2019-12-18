package bBlackjackFunctional

typealias Action<T> = (T) -> Unit
typealias Transform<T> = (T) -> T

typealias TransformThis<T> = T.() -> T

fun <T> List<T>.shuffleIf(shuffle: Boolean): List<T> {
    return if (shuffle) shuffled() else this
}

fun <T> T.transformIf(test: Boolean, transform: (T) -> T): T {
    return if (test) transform(this) else this
}

fun <T> T.transformIf2(test: Boolean, transform: T.() -> T): T {
    return if (test) this.transform() else this
}



fun List<Card>.dump() {
    forEach {
        println(it)
    }
}