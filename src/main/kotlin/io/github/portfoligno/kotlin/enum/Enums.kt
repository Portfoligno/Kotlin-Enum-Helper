package io.github.portfoligno.kotlin.enum

import java.lang.ref.WeakReference
import java.util.*

private
// Use weak references to allow garbage collection of enum classes
val enumConstants: MutableMap<Class<*>, Map<String, WeakReference<*>>> = WeakHashMap()

fun <E : Enum<E>> getIfPresent(enumClass: Class<E>, value: String): E? {
  val map = enumConstants.getOrPut(enumClass) {
    EnumSet
      .allOf(enumClass)
      .associateBy({ it.name }, ::WeakReference)
  }

  @Suppress("UNCHECKED_CAST")
  return map[value]?.get() as E?
}

inline fun <reified E : Enum<E>> enumOf(name: String): E? =
    getIfPresent(E::class.java, name)
