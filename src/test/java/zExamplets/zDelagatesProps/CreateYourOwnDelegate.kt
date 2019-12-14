package zExamplets.zDelagatesProps

import kotlin.reflect.KProperty

fun main(args: Array<String>) {

    val p1 = Person(1)
    val p2 = Person(2)

    p1.firstName = "Dave"
    p1.lastName = "Ford"

    p2.firstName = "Joe"
    p2.lastName = "blow"

    println(p1)
    println(p2)


}

class Person(id: Id) : Entity(id) {

    var firstName: String? by mp()
    var lastName: String? by mp()

    override fun toString(): String {
        return "$id $firstName $lastName"
    }
}


fun <T> mp() = DataFromMapDelegate<T>()


typealias Record = HashMap<PropName, Any?>

abstract class Entity(val id: Int) {
    val map = Record()
}

typealias Id = Int
typealias PropName = String

class DataFromMapDelegate<T> {

    operator fun getValue(entity: Entity, property: KProperty<*>): T? {
        return entity.map[property.name] as T?
    }

    operator fun setValue(entity: Entity, property: KProperty<*>, value: T) {
        entity.map[property.name] = value
    }


}