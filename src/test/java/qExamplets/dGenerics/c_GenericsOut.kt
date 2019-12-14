package qExamplets.dGenerics

open class Exp
class Lit : Exp()
class And : Exp()

/*
Out is a producer of Ts
Out is covariant with respect to T
 */
class HolderR<out T>(v: T) {
    private val _v = v
    fun getValue(): T {
        return _v
    }
}

class HolderW<in T> {
    fun setValue(v: T) {
        println(v)
    }
}

fun main(args: Array<String>) {
    val expR: HolderR<Exp> = HolderR<Exp>(And())
    val litR: HolderR<Lit> = HolderR<Lit>(Lit())
    val expW: HolderW<Exp> = HolderW<Exp>()
    val litW: HolderW<Lit> = HolderW<Lit>()

//    mutateExp(litW)
    mutateLit(expW)

    readExp(litR)
//    readLit(expR)

}

fun mutateExp(h: HolderW<Exp>) {
    h.setValue(And())
//    val e:Exp = h.getValue()
}

fun mutateLit(h: HolderW<Lit>) {
    h.setValue(Lit())
//    val e:Lit = h.getValue()
}

fun readExp(h: HolderR<Exp>) {
    val e: Exp = h.getValue()
//    h.setValue(And())
}

fun readLit(h: HolderR<Lit>) {
    val e: Lit = h.getValue()
//    h.setValue(Lit())
}
