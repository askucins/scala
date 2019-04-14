package org.askucins

import org.scalatest._

class HelloWithFeatureSpec extends FeatureSpec with GivenWhenThen {
  info("A new greeting object")
  info("should start with Hello")

  feature("Greeting starts with Hello") {
    scenario("User created a new greeting") {
      Given("A Hello object")
      val hello = Hello
      When("a new greeting is created")
      val greeting = hello.greeting
      Then("it starts with Hello")
      greeting.startsWith("Hello")
    }
  }
}
