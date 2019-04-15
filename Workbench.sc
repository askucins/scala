val a = List ("a", "b")
val b = List("a", "c")

a ++ b
List(a, b).reduce(_++_)

