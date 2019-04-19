package ShapingUp.Task1

class Square(a:Int) extends Shape {
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
