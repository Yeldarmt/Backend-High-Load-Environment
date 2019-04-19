package opt

object Options extends App{

  def calculator(operand1: String, operator: String, operand2: String): Option[Int] = {

    def sum(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 + value2
    }

    def div(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      opt2 match {
        case Some(a) if a == 0 => return None
        case _ =>
      }

      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 / value2
    }

    def sub(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 - value2
    }

    def product(opt1: Option[Int], opt2: Option[Int]): Option[Int] =  {
      for {
        value1 <- opt1
        value2 <- opt2
      } yield value1 * value2
    }

    val a1: Option[Int] = if(operand1 matches "\\d+") Some(operand1.toInt) else None
    val a2: Option[Int] = if(operand2 matches "\\d+") Some(operand2.toInt) else None


    operator match {
              case "+" => sum(a1, a2)
              case "-" => sub(a1, a2)
              case "*" => product(a1, a2)
              case "/" => div(a1, a2)
    }


  }

  println(calculator("3", "*", "2"))
  println(calculator("3", "/", "2"))
}
