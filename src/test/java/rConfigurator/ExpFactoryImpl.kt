package rConfigurator

class ExpFactoryImpl : ExpFactory {
    override fun and() {
        println("and")
    }

    override fun or() {
        println("or")
    }
}

class Ctx(val a: Assignments, val ef: ExpFactory) : Assignments by a, ExpFactory by ef {


}

fun main(args: Array<String>) {
    val c = Ctx(a = MutableAssignments(), ef = ExpFactoryImpl())

    c.and()
}

class DaveEntityManager(ef: ExpFactory) : ExpFactory by ef {
    override fun or() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}