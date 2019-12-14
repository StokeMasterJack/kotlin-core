package eConfigurator

class VarConflictException(val lit: Lit) : Exception() {
    override val message: String get() = "$lit"
}

class MutableAssignments(
    override val t: MutableSet<Var> = mutableSetOf(),
    override val f: MutableSet<Var> = mutableSetOf()) : Assignments {

    constructor(lits: List<Lit>) : this() {
        assign(lits)
    }

    constructor(a: Assignments) : this(a.t.toMutableSet(), a.f.toMutableSet())

    @Throws(VarConflictException::class)
    fun assign(lits: List<Lit>): Boolean {
        var anyChange = false
        for (lit in lits) {
            val ch = assign(lit)
            if (ch) anyChange = true
        }
        return anyChange
    }

    @Throws(VarConflictException::class)
    fun assign(a: Assignments): Boolean {
        var anyChange = false
        for (v in a.t) {
            val ch = assign(v)
            if (ch) anyChange = true
        }
        for (v in a.f) {
            val ch = assign(v.neg)
            if (ch) anyChange = true
        }
        return anyChange
    }

    @Throws(VarConflictException::class)
    fun assign(lit: Lit): Boolean = when (lit) {
        is Var -> {
            if (f.contains(lit)) throw VarConflictException(lit)
            t.add(lit)
        }
        is NegVar -> {
            if (t.contains(lit.v)) throw VarConflictException(lit)
            f.add(lit.v)
        }
    }

    override fun toString(): String {
        return toLits().toString()
    }
}