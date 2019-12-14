package qExamplets.dGenerics

/*
In is a consumer of Ts
In is contravariant with respect to T
 */
class In<in T> {

    fun setValue(v: T) {
        println(v)
    }
}

fun main(args: Array<String>) {
    val i1: HolderW<Int> = HolderW()
//    val a1: In<Any> = i1 //no good


    val a2: HolderW<Any> = HolderW()
    val i2: HolderW<Int> = a2   //ok


}

