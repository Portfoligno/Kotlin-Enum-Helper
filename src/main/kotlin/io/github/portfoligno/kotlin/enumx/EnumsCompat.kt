package io.github.portfoligno.kotlin.enumx

// Provide source compatibility for the name change (The JVM name remains unchanged)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Use enumOf", ReplaceWith("enumOf(enumClass, name)"))
inline fun <T : Enum<T>> getOrNull(enumClass: Class<T>, name: String): T? =
    enumOf(enumClass, name)
