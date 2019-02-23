package AlgebraicDataTypes

//task1
sealed trait Calculator
case class Int() extends Calculator
case class String() extends Calculator

//task2
case class BottledWater(size: Int,source: String,carbonated: Boolean)

//or
trait Bottled_Water{
  def size: Int
  def source: String
  def carbonated: Boolean
}