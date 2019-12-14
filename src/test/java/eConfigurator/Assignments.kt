package eConfigurator

interface Assignments {

    val t: Set<Var>
    val f: Set<Var>

    fun value(v: Var): Tri = when {
        t.contains(v) -> Tri.TRUE
        f.contains(v) -> Tri.FALSE
        else -> Tri.OPEN
    }

    fun isAssigned(v: Var): Boolean = t.contains(v) || f.contains(v)

    val vars: Set<Var> get() = t + f

    fun anyOverlap(vars: Set<Var>): Boolean {
        return vars.any { t.contains(it) || f.contains(it) }
    }

    fun isDisjoint(vars: Set<Var>): Boolean = !anyOverlap(vars)

    fun isEmpty(): Boolean = t.isEmpty() && f.isEmpty()

    fun toLits(): Set<Lit> {
        return t + f.map { it.neg }
    }

}