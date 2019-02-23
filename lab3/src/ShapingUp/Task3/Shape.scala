package ShapingUp.Task3

sealed trait Shape {
  def Sides(): Int
  def Perimetre(): Double
  def Area(): Double
}


