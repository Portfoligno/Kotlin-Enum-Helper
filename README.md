[![](https://jitpack.io/v/io.github.portfoligno/kotlin-enum-helper.svg)](
https://jitpack.io/#io.github.portfoligno/kotlin-enum-helper/$VERSION)
# Kotlin Enum Helper
A small utility to get Java enums by name, with reified generics support.

For those who try to avoid introducing massive dependencies (e.g. library authors),
or are searching around for slight syntax improvements of enum parsing, this library may fit your need.

Example
----
### Kotlin
```kotlin
enum class Color {
  RED,
  GREEN,
  BLUE
}

fun parseUserInput(colorName: String): Color =
  enumOf<Color>(colorName) ?: Color.RED // Default to RED
```

### Java
```java
enum Color {
  RED,
  GREEN,
  BLUE;

  static Color parseUserInput(String colorName) {
    Color color = Enums.getOrNull(Color.class, colorName);
    return color != null ? color : Color.RED; // Default to RED
  }
}
```

*Please note that Java usage is not recommended.
(Unless you have proper measures to deal with `null` references)*

Project setup
----
Please refer to instructions on [JitPack](https://jitpack.io/#io.github.portfoligno/kotlin-enum-helper/$VERSION).

### Gradle (Kotlin DSL)
```kotlin
repositories {
  maven(url = "https://jitpack.io")
}
dependencies {
  implementation("io.github.portfoligno:kotlin-enum-helper:$VERSION")
}
```

Comparisons to alternatives
----
### `MyEnum#valueOf` / `Enum#valueOf` (JDK) / `enumValueOf` (Kotlin stdlib)

The standard methods of JDK / Kotlin Standard Library.

Instead of returning `null`, `IllegalArgumentException` is thrown when the supplied name is not valid.
Useful when:

* You have evidence that every name supplied should match an enum constant

If unmatched names is not an exceptional case,
using these methods could introduce unnecessary verbosity in your code.

### `Enums#getIfPresent` (Guava)

The original method this library is based on.

Instead of returning null for unmatched names, every result is wrapped in an `Optional`,
giving additional safety in compensation of GC overheads.

If you are using Kotlin, you already have that null-safety.
(One possibility is that you could end up using `Optional#orNull` everywhere after this method.)

### `EnumUtils#getEnum` (Apache Commons Lang)

Functionally identical to `Enums#getOrNull` / `enumOf` of this library.

A subtle difference would be the return type is not marked `@Nullable`,
and hence callers must handle the return value with awareness in term of null-safety.

Performance-wise, instead of using internal maps for the enum constants,
`IllegalArgumentException` is caught every time when an invalid name is supplied.
Depending on the frequency of invalid inputs, the overheads of exception handling and GC may vary.

### Standard methods with `Try` / `Either` monad

Depends on what library you are using, you may achieve handling for a non-exceptional case like:

```java
Either<Throwable, Color> userInputColor = Either
    .catching(() -> Color.valueOf(colorName))
    .recoverWith(IllegalArgumentException.class, ignored -> Color.RED) // Default to RED
```

This is a more generic solution, provided it can handle more than only unmatched enum names.
And you gain the opportunity to delegate handling to wherever appropriate as well.

On the contrary, it could require a more functional convention in the project,
when compared to some simple use of nullable or `Optional` types.
