@file:JvmName("Enums")
package io.github.portfoligno.kotlin.enumx

import java.lang.ref.WeakReference
import java.util.*

private
// Use weak references to allow garbage collection of enum classes
val enumConstants: MutableMap<Class<*>, Map<String, WeakReference<*>>> = WeakHashMap()

/**
 * Return an enum constant for the given type and name or `null` if not found.
 *
 * @see [java.lang.Enum.valueOf]
 * @see [enumOf]
 */
fun <E : Enum<E>> getOrNull(enumClass: Class<E>, name: String): E? {
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
inline fun <reified E : Enum<E>> enumOf(name: String): E? =
    getOrNull(E::class.java, name)
