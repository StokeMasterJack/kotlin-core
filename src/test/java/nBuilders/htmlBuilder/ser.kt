package nBuilders.htmlBuilder

private const val indentSize = 4
private val tab = " ".repeat(indentSize)
private val nl = System.getProperty("line.separator")!!
private fun indent(depth: Int) = tab.repeat(depth)

val El.n: String get() = tagName
val El.startTag: String get() = "<$n>"
val El.endTag get() = "</$n>"
val El.emptyTag get() = "<$n/>"

fun Node.joinNodes(sep: String, depth: Int): String = nodes.joinToString(separator = sep) { it.ser(depth) }

fun Node.ser(depth: Int = 0): String {

    val to = containsOnlyTextContent

    fun Node.serChildContent(): String = when (this) {
        is Text -> text
        is El -> when {
            isEmpty -> ""
            to -> joinNodes(" ", 0)
            else -> joinNodes("", depth + 1)
        }
    }

    val ch = serChildContent()

    return when (this) {
        is Text -> ch
        is El -> {
            val dent = indent(depth)
            when {
                isEmpty -> "$dent$emptyTag$nl"
                else -> {
                    val start = if (to) "$dent$startTag" else "$dent$startTag$nl"
                    val end = if (to) "$endTag$nl" else "$dent$endTag$nl"
                    "$start$ch$end"
                }
            }
        }
    }
}




