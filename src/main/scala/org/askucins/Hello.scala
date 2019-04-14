package org.askucins

object Hello extends Greeting with App {
  println(greeting)
  sayMorning()
}

trait Greeting {
  lazy val greeting: String = "Hello, world!"

  def sayMorning(): Unit = println("Good morning!")
}
