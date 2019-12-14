package qExamplets.dGenerics

/*
Both is a consumer and producer of Ts
Both is invariant with respect to T
 */
class Both<T>(var v: T)

fun main(args: Array<String>) {
    val i1: Both<Int> = Both(11)
//    val a1: Both<Any> = i1 //no good

    val a2: Both<Any> = Both(33)
//    val i2: Both<Int> = a2   //no good


}

