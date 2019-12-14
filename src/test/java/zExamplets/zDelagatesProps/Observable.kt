package zExamplets.zDelagatesProps

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


object Bus {
    fun onChange(obj: Any, prop: KProperty<*>, old: Any?, new: Any?) {
        println(prop)
        println(old)
        println(new)
        println()
    }
}

fun del(obj: Any) = Delegates.observable(10) { prop, old, new ->
    Bus.onChange(obj, prop, old, new)
}

class Box {
    var length: Int by del(this)
    var width: Int by del(this)
}

fun main(args: Array<String>) {
    val b = Box()
    b.length = 5
    b.length = 10

}

