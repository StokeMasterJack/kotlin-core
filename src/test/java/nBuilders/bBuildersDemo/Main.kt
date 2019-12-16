package nBuilders.bBuildersDemo

import nBuilders.htmlBuilder.Node
import nBuilders.htmlBuilder.ser

val people: List<Person> = listOf(
    Person("Dave", "Ford"),
    Person("Kellie", "Jones"),
    Person("Fred", "Smith")
)

fun main(args: Array<String>) {
    val node: Node = Report.mk(people)
    println(node.ser())
}