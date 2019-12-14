package zExamplets.zDelagatesProps

class User(val map: MutableMap<String, Any?> = mutableMapOf()) {

    var name: String by map
    var age: Int by map

    override fun toString(): String {
        return "$name $age"
    }
}

fun main(args: Array<String>) {
    val u1 = User(mutableMapOf("name" to "Dave", "age" to 50))
    println(u1)
    u1.age = 30
    println(u1)


}