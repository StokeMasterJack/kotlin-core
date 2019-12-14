package zExamplets.zStandardFunctions

//same as run2 but uses it instead of this
fun main(args: Array<String>) {

    val r = Box(
        x = 10,
        y = Foo.let {
            val a = 1 + 2 + 3
            val b = 4 + 5 + 6
            val c = 7 + 8 + 9
            a + b + c + it.xx
        },
        z = 44
    )

}





