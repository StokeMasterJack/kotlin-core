package zExamplets.zStandardFunctions

/**
 * Problem:
 *      you want to pass a value
 *      the value is the result of an expression
 *      the expression is long and harry
 *      using some local vars would make the expression more readable
 *      those local vars are not needed other than on that one spot
 */
fun main(args: Array<String>) {

    val r = Box(
        x = 10,
        y = run {
            val a = 1 + 2 + 3
            val b = 4 + 5 + 6
            val c = 7 + 8 + 9
            a + b + c
        },
        z = 44
    )

}


