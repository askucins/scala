package org.askucins

import org.scalatest._

class HelloWithFlatSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    Hello.greeting shouldEqual "Hello, world!"
  }
}
