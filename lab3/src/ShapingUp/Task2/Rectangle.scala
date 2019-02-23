package ShapingUp.Task2

class Rectangle(a:Int,b:Int) extends Rectangular {
  override def Area(): Double = {
    a*b
  }

  override def Perimetre(): Double = {
    2*(a+b)
  }

  override def Sides(): Int = {
    4
  }
}
