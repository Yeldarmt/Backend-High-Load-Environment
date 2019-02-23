package ShapingUp.Task3

case class Square(a:Int) extends Rectangular {
  override def Area(): Double = {
    Math.pow(a,2)
  }

  override def Perimetre(): Double = {
    4*a
  }

  override def Sides(): Int = {
    4
  }
  def getSide(): Int = {
    return a
  }
}
