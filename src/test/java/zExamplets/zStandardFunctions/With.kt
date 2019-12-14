package zExamplets.zStandardFunctions

/**
 * Very similar to run2.
 *  run2 is an extension function (uses receiver)
 *  with requires an arg
 *
 */
fun main(args: Array<String>) {

    val b = Box(1, 1, 1)

    val sum: Int = with(b) {
        x + y + z
    }


}
    