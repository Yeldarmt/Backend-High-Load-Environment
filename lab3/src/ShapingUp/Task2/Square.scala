package ShapingUp.Task2

class Square(a:Int) extends Rectangular {
  override def Area(): Double = {
    Math.pow(a,2)
  }

  override def Perimetre(): Double = {
    4*a
  }

  override def Sides(): Int = {
    4
  }
}
object obj extends App{
  val square = new Square(4)
  println(square.Area())
  println(square.Perimetre())
  println(square.Sides())
}
