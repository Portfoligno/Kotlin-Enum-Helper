@file:JvmName("Enums")
package io.github.portfoligno.kotlin.enumx

import java.lang.ref.WeakReference
import java.util.*
import kotlin.reflect.KClass

private
// Use weak references to allow garbage collection of enum classes
val enumConstants: MutableMap<Class<*>, Map<String, WeakReference<*>>> = WeakHashMap()

/**
 * Return an enum constant for the given type and name or `null` if not found.
 *
 * @see [java.lang.Enum.valueOf]
 */
@JvmName("getOrNull")
fun <T : Enum<T>> enumOf(enumClass: Class<T>, name: String): T? {
  // Inlined closures
  val map = enumConstants.getOrPut(enumClass) {
    EnumSet
        .allOf(enumClass)
        .associateBy({ it.name }, ::WeakReference)
  }
  return enumClass.cast(map[name]?.let {
    it.get() ?: throw AssertionError("Empty reference") // impossible
  })
}

/**
 * Return an enum constant for the given type and name or `null` if not found.
 *
 * @see [java.lang.Enum.valueOf]
 */
@Suppress("NOTHING_TO_INLINE") // Hide from Java callers
inline fun <T : Enum<T>> enumOf(enumClass: KClass<T>, name: String): T? =
    enumOf(enumClass.java, name)

/**
 * Return an enum constant for the given type and name or `null` if not found.
 *
 * @see [enumValueOf]
 */
inline fun <reified T : Enum<T>> enumOf(name: String): T? =
    enumOf(T::class, name)
