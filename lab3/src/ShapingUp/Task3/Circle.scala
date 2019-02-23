package ShapingUp.Task3

case class Circle(radius: Double) extends Shape {
  override def Area(): Double = {
    var area: Double = 0
    area = Math.PI * radius * radius
    return area
  }

  override def Sides(): Int = {
    var num: Int = 0
    return num
  }

  override def Perimetre(): Double = {
    var per: Double = 0
    per = 2 * Math.PI * radius
    return per
  }
  def getRadius(): Double={
    return radius;
  }
}

