package org.askucins.hello

import org.scalatest._

class HelloWithFunSuite extends FunSuite {
  test("Should greeting start with Hello") {
    assert(Hello.greeting.startsWith("Hello"))
  }
}
