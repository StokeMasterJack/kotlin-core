package rConfigurator


/*
Exp
  Simple
    Constant
    Lit
  Complex
    Not
    Pair
    Nary

 */


typealias VarId = String

object True : Constant()
object False : Constant()

fun main(args: Array<String>) {

}

sealed class Exp {

    companion object {
        fun ensureNoConstants(exps: List<Exp>) {
            check(exps.any { it !is Constant })
        }

        @JvmStatic
        fun ensureNoConstants(e1: Exp, e2: Exp) {
            check(e1 !is Constant)
            check(e2 !is Constant)
        }

        fun and(exps: List<Exp>): Exp = when (exps.size) {
            0 -> True
            1 -> exps[0]
            else -> And(exps)
        }

        fun or(exps: List<Exp>): Exp = when (exps.size) {
            0 -> False
            1 -> exps[0]
            else -> Or(exps)
        }
    }

    val vars: Set<Var>
        get() {
            return mutableSetOf<Var>().apply {
                visit { if (it is Lit) add(it.v) }
            }
        }

    fun visit(action: (e: Exp) -> Unit) {
        action(this)
        if (this is Complex) exps.forEach { action(it) }
    }

    fun isAffected(a: Assignments): Boolean = when (this) {
        is Constant -> false
        is Var -> a.isAssigned(this)
        is NegVar -> a.isAssigned(this.v)
        is Complex -> exps.any { it.isAffected(a) }
    }

    fun maybeSimplify(a: Assignments): Exp {
        if (!isAffected(a)) return this
        return simplify(a)
    }

    //    open fun simplify(a: Assignments,f:ExpFactory): Exp = throw UnsupportedOperationException()
//    open fun simplify(ctx:Ctx): Exp = throw UnsupportedOperationException()
    open fun simplify(a: Assignments): Exp = throw UnsupportedOperationException()

    val op: String get() = this::class.simpleName!!.toLowerCase()

    final override fun toString(): String {
        return when (this) {
            is Constant -> op
            is Var -> id
            is NegVar -> "!${v.id}"
            is Complex -> "$op$exps"
        }
    }

    fun flip(): Exp = when (this) {
        is Constant -> throw IllegalStateException()
        is Var -> neg
        is NegVar -> v
        is Not -> exp
        is Complex -> Not(this)
    }

}

sealed class Simple : Exp()

sealed class Complex : Exp() {
    abstract val exps: List<Exp>
}

sealed class Nary(final override val exps: List<Exp>) : Complex() {

    init {
        ensureNoConstants(exps)
        check(exps.size > 1)
    }

}

sealed class Pair(val e1: Exp, val e2: Exp) : Complex() {

    init {
        ensureNoConstants(e1, e2)
    }

    override val exps: List<Exp>
        get() = listOf(e1, e2)
}

data class Not(val exp: Exp) : Complex() {

    init {
        check(exp is Complex)
        check(exp !is Not)
    }

    override val exps: List<Exp> get() = listOf(exp)
}


sealed class Lit : Simple() {
    abstract val v: Var
}

sealed class Constant : Simple()

class Var(val id: VarId) : Lit(), Comparable<Var> {

    val neg: NegVar by lazy {
        NegVar(this)
    }

    override val v: Var get() = this

    override fun simplify(a: Assignments) = when (a.value(this)) {
        Tri.TRUE -> True
        Tri.FALSE -> False
        Tri.OPEN -> throw IllegalStateException()
    }

    override fun compareTo(other: Var) = id.compareTo(other.id)

    override fun hashCode() = id.hashCode()

    override fun equals(other: Any?) = other is Var && other.id == id

}

class NegVar(override val v: Var) : Lit() {
    override fun simplify(a: Assignments) = when (a.value(this.v)) {
        Tri.TRUE -> False
        Tri.FALSE -> True
        Tri.OPEN -> throw IllegalStateException()
    }
}

class Conflict(e1: Exp, e2: Exp) : Pair(e1, e2) {
    override fun simplify(a: Assignments): Exp {
        val s1 = e1.maybeSimplify(a)
        val s2 = e1.maybeSimplify(a)
        if (s1 == True && s2 == True) return False
        if (s1 == False || s2 == False) return True
        if (s1 == True) return s2.flip()
        if (s2 == True) return s1.flip()
        return Conflict(s1, s2)
    }
}

class Requires(e1: Exp, e2: Exp) : Pair(e1, e2) {
    override fun simplify(a: Assignments): Exp {
        val s1 = e1.maybeSimplify(a)
        val s2 = e2.maybeSimplify(a)
        if (s1 == True && s2 == False) return False
        if (s1 == False) return True
        if (s2 == True) return True
        if (s1 == True) return s2
        if (s2 == False) return s1.flip()
        return Requires(s1, s2)
    }
}

/**
 * Unlike a csp, there may be overlap between assignments and constraints
 * The lits represent inferred assignments, these will be used for a second round of simplification
 */
data class Split(val lits: List<Lit>, val constraint: Exp)

class And(exps: List<Exp>) : Nary(exps) {

    override fun simplify(a: Assignments): Exp {
        val ss = mutableListOf<Exp>()
        for (e in exps) {
            check(e !is Constant)
            val s = e.maybeSimplify(a)
            if (s == True) continue
            else if (s == False) return False
            else if (s is And) ss.addAll(s.exps)
            else ss.add(s)
        }
        return and(ss)
    }

    val anyTopLevelLits get() = exps.any { it is Lit }

    private val topLevelLits: List<Lit> = exps.filterIsInstance(Lit::class.java)
    private val topLevelComplex: List<Exp> = exps.filterIsInstance(Complex::class.java)

    fun split(): Split {
        val lits = topLevelLits
        val constraint = and(topLevelComplex)
        return Split(lits, constraint)
    }

    val isPure: Boolean get() = !anyTopLevelLits
}

class Or(exps: List<Exp>) : Nary(exps) {
    override fun simplify(a: Assignments): Exp {
        val ss = mutableListOf<Exp>()
        for (e in exps) {
            check(e !is Constant)
            val s = e.maybeSimplify(a)
            if (s == False) continue
            else if (s == True) return True
            else if (s is Or) ss.addAll(s.exps)
            else ss.add(s)
        }
        return or(ss)
    }
}

