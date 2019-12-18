package dExamplets.a1OperatorsInfix

enum class ModKey {
    H, W, C
}

sealed class Mod {

    val key: ModKey
        get() = when (this) {
            is Height -> ModKey.H
            is Width -> ModKey.W
            is Color -> ModKey.C
        }
    val pair: Pair<ModKey, Mod> get() = Pair(key, this)

    override fun toString(): String = when (this) {
        is Length -> "$key $v"
        is Color -> "$key $r $g $b"
    }


}

sealed class Length(val v: Int) : Mod()
class Height(v: Int) : Length(v)
class Width(v: Int) : Length(v)
class Color(val r: Int, val g: Int, val b: Int) : Mod()

class Ints(val a: List<Int> = emptyList(), val map: Map<ModKey, Mod> = emptyMap()) {

    infix fun wrap(m: Mod): Ints {
        return Ints(a, map + m.pair)
    }

    override fun toString(): String {
        return "Foo  a:$a mods:$map"
    }

    operator fun plus(i: Int): Ints {
        return Ints(a + i)
    }

    operator fun contains(key: ModKey): Boolean {
        return map.containsKey(key)
    }

}

fun main(args: Array<String>) {
    val i1: Ints = Ints()
    val i2: Ints = i1 + 33 + 11
    println(i2)
    val i3 = i2 wrap Height(22) wrap Width(2)
    val i4 = i3 wrap Color(1, 1, 1)
    println(i4)

    println(ModKey.C in i3)
    println(ModKey.C in i4)
}