package b3BlackjackServer

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.reflect.KClass


fun HttpServletResponse.sendError500(e: Throwable, msg: String? = null) {
    val m = msg ?: e.message
    this.status = 500
    this.contentType = "text/plain; charset=utf-8"
    this.writer.println(msg)
}

fun HttpServletResponse.setContentTypeHtml() {
    this.contentType = "text/html; charset=utf-8"
}

fun HttpServletResponse.sendHtml(html: String) {
    this.setContentTypeHtml()
    this.writer.print(html)
}

class MissingRequiredParameterException(val name: String) : RuntimeException() {
    override val message: String?
        get() = "$name is a required parameter"
}

fun HttpServletRequest.paramString(name: String): String? {
    val s = this.getParameter(name) ?: return null
    if (s.trim().isEmpty()) return null
    return s.trim()
}

fun HttpServletRequest.paramStringRequired(name: String): String {
    return paramString(name) ?: throw MissingRequiredParameterException(name)
}

fun <T : Enum<T>> HttpServletRequest.paramEnum(name: String, enumClass: KClass<T>): T? {
    val sValue = paramString(name) ?: return null
    return java.lang.Enum.valueOf(enumClass.javaObjectType, sValue)
}

fun <T : Enum<T>> HttpServletRequest.paramEnumRequired(name: String, enumClass: KClass<T>): T {
    val sValue = paramStringRequired(name)
    return java.lang.Enum.valueOf(enumClass.javaObjectType, sValue)
}

