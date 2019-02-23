package ShapingUp.Task3

object Draw {
  def apply(shape:Shape): String={
    shape match {
      case x: Circle => return s"A circle of radius ${x.getRadius()}cm"
      case x: Rectangle => return s"A rectangle of width ${x.getWidth()}cm and height ${x.getHeight()}cm"
      case x: Square => return s"A square of side ${x.getSide()}cm "
      case _ => return s"This type not found";
    }
  }
}

object test extends App{
  //val s = new Square(5)
  //val r = new Rectangle(3,4)
  //val c = new Circle(5)
  println(Draw.apply(Square(5)))
  println(Draw(Rectangle(4,5)))
  println(Draw(Circle(5)))
}
