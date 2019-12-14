package eConfigurator

fun mkCsp(action: CspBuilder.() -> Unit): Csp {
    val b = CspBuilder()
    b.action()
    return b.mk()
}

class CspBuilder {

    private val vars = mutableSetOf<Var>()
    private val constraints = mutableListOf<Exp>()

    private fun mkVar(varId: VarId): Var {
        val v = Var(varId)
        vars.add(v)
        return v
    }

    operator fun String.unaryPlus(): Var {
        return mkVar(this)
    }

    fun add(e: Exp) {
        constraints.add(e)
    }

    fun conflict(e1: Exp, e2: Exp) {
        add(Conflict(e1, e2))
    }

    fun requires(e1: Exp, e2: Exp) {
        add(Requires(e1, e2))
    }

    fun and(vararg exps: Exp): Exp {
        return Exp.and(exps.toList())
    }


    fun or(vararg exps: Exp): Exp {
        val xx = exps.toList()
        val oo = Exp.or(xx)
        return oo
    }

    fun mk(): Csp = Csp(vars, And(constraints))


}
