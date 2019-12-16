package rConfigurator

/**
 * Contract for a Csp
 *      constraint will never contain lits
 *      constraint will never be a lit
 *      constraint will never contain constants (thought it may *be* a constant)
 *      assignments and constraint are always disjoint (thus it is a DAnd)
 *
 *      thus, possible values for constraint:
 *          True
 *          False
 *          And(with no lits or constants)
 *          Or, Conflict, Requires,Not
 *
 */
class Csp(val vars: Set<Var>, val constraint: Exp, val assignments: Assignments = MutableAssignments()) {

    init {
        require(assignments.isDisjoint(constraint.vars))
    }

    fun print() {
        println("Assignments: ${assignments}")
        println("Constraints:")
        if (constraint is And) {
            for (e in constraint.exps) {
                println("  $e")
            }
        } else {
            println("  $constraint")
        }
        println()
    }

    fun maybeSimplify(a: Assignments): Csp {
        require(a.isDisjoint(assignments.vars))

        val aa = MutableAssignments(a)

        var current: Exp = constraint

        try {
            while (true) {
                val after = current.maybeSimplify(aa)
                if (after === current) break
                if (after is Constant) {
                    current = after
                    break
                } else if (after is Lit) {
                    aa.assign(after)
                    current = True //an empty and is same as True
                } else if (after is And && after.anyTopLevelLits) {
                    val (lits, constraint) = after.split()
                    aa.assign(lits)
                    current = constraint
                } else {
                    check((after is And && after.isPure) || (after !is Complex && after is Complex))
                    current = after
                }
            }
        } catch (e: VarConflictException) {
            current = False
        }

        aa.assign(assignments)
        return Csp(vars, current, aa)
    }

    fun maybeSimplify(lits: List<Lit>): Csp {
        return maybeSimplify(MutableAssignments(lits))
    }

    fun maybeSimplify(vararg lits: Lit): Csp {
        return maybeSimplify(lits.asList())
    }

    val constraintVars: Set<Var> get() = constraint.vars

    val openVars: Set<Var> get() = vars - assignments.vars

    val isEmpty = assignments.isEmpty() && constraint == True

    val isFailed = constraint == False


}