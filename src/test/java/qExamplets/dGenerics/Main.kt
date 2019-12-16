package qExamplets.dGenerics

import rConfigurator.Exp
import rConfigurator.Lit

fun main(args: Array<String>) {
    val a1: List<Lit> = listOf()
    val a2: List<Exp> = listOf()
    f11(a1)
//    f22(a2)
}

fun f11(list: List<Exp>) {
//    list.add(And())
    val e: Exp = list.get(0)
}

fun f22(list: List<Lit>) {
//    list.add(Lit())
//    val e: Exp = list.get(0)
}

    