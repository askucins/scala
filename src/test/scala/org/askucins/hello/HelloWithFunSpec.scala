package org.askucins.hello

import org.scalatest._

class HelloWithFunSpec extends FunSpec {
  describe("A greeting") {
    it ("should start with Hello") {
      assert(Hello.greeting.startsWith("Hello"))
    }
  }
}