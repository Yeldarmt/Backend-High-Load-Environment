package ShapingUp.Task3

case class Rectangle(a:Int,b:Int) extends Rectangular {
  override def Area(): Double = {
    a*b
  }

  override def Perimetre(): Double = {
    2*(a+b)
  }

  override def Sides(): Int = {
    4
  }
  def getWidth(): Int={
    return a
  }
  def getHeight(): Int={
    return b
  }
}
