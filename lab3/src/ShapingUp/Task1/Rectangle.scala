package ShapingUp.Task1

import java.awt.Rectangle

class Rectangle(a:Int,b:Int) extends Shape{
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
object as extends App{
  val rec = new Rectangle(4,5)
  println(rec.Area());
  println(rec.Perimetre());
  println(rec.Sides());
}
