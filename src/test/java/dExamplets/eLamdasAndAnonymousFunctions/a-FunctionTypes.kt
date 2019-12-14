package dExamplets.eLamdasAndAnonymousFunctions

typealias VF = () -> Unit
typealias IntToInt = (Int) -> Int
typealias IntsToInt = (Int, Int) -> Int

//fun useFunction(f1: () -> Unit, f2: (Int) -> Int) {
//    f1()
//    println(f2(2))
//}

fun useFunction(f0: VF, f1: IntToInt, f2: IntsToInt? = null) {
    f0()
    println(f1(2))
    if (f2 != null) {
        println(f2(2, 3))
    }
}