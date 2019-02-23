package Recursion_tasks

sealed trait IntList {
  def length(l: Int = 0): Int = this match {
    case End => l
    case Node(_, tail) => tail.length(l + 1)
  }
  def product(p: Int = 1): Int = this match {
    case End => p
    case Node(head, tail) => tail.product(p * head)
  }

  def double: IntList = this match {
    case End => End
    case Node(head, tail) => Node(head * 2, tail.double)
  }

}
case object End extends IntList
case class Node(head: Int, tail: IntList) extends IntList


object List extends App{
  val intList = Node(1, Node(2, Node(3, Node(4, End))))

  println(intList.length() == 4)
  println(intList.tail.length() == 3)
  println(End.length() == 0)

  println(intList.product() == (1 * 2 * 3 * 4))
  println(intList.tail.product() == 2 * 3 * 4)
  println(End.product() == 1)

  println(intList.double == Node(1 * 2, Node(2 * 2, Node(3 * 2, Node(4 * 2, End)))))
  println(intList.tail.double == Node(4, Node(6, Node(8, End))))
  println(End.double == End)
}
