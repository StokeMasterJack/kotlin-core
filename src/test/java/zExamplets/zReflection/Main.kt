package zExamplets.zReflection

import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.node.ObjectNode
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


open class Foo
class Box(var length: Int, var width: Int, var height: Int) : Foo()

fun main(args: Array<String>) {
    val json: ObjectNode = ObjectMapper().createObjectNode()

    val oo: Foo = Box(1, 1, 1)
    ser(oo)
}

inline fun <T : Any> ser(o: T) {
    val fields = o::class.memberProperties
    fields.forEach {
        println(it)
        val mutableField = it as? KProperty<*>
        mutableField?.let {
            if (!it.isAccessible) {
                it.isAccessible = true
            }

            val result = it.getter.call(o)
            println(mutableField.name + " has value " + result.toString())
        }
    }
}





