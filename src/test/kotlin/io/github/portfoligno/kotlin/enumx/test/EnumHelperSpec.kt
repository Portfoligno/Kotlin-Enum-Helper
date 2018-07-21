package io.github.portfoligno.kotlin.enumx.test

import io.github.portfoligno.kotlin.enumx.enumOf
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

enum class Color {
  RED,
  GREEN,
  BLUE
}

class EnumHelperSpec : StringSpec({
  "Get RED should be RED" {
    enumOf<Color>("RED") shouldBe Color.RED
  }
  "Get GREEN should be GREEN" {
    enumOf<Color>("GREEN") shouldBe Color.GREEN
  }
  "Get BLUE should be BLUE" {
    enumOf<Color>("BLUE") shouldBe Color.BLUE
  }
  "Get YELLOW should be null" {
    enumOf<Color>("YELLOW") shouldBe null
  }
})
