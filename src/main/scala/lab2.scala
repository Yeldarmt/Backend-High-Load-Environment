package lab_2
// Theoretical questions: why do we need abstraction
/*/Abstraction will only expose the relevant information to outside world. It will provide the necessary
information to the user and hide unnecessary information from the user*/
// How `traits` in Scala are used?
//to extend in classes

trait Animal {
  // Is this abstract or concrete (implemented) member?
  // abstract because method is no complete
  def name: String

  // Is this abstract or concrete (implemented) member?
  //abstract because method is no complete
  def makeSound(): String
}

trait Walks {

  // What does this line mean?
  // this is done to mixin 2 traits event they dont extend each other
  this: Animal =>

  // Is this abstract or concrete (implemented) member?
  // concrete because our method is complete
  // Why `name` parameter is available here?
  //
  def walk: String = s"$name is walking"

}


// Can Dog only extend from `Walks`?
// no, because 'Walks' can extend many traits

// Try to fix Dog, so it extends proper traits
// Implement Dog class so it passes tests
case class Dog(_name: String, sound: String = "Whooof") extends Walks with Animal{
  override def name: String = _name
  override def makeSound(): String = sound;

}

// Implement Cat class so it passes tests
case class Cat(_name: String, sound: String = "Miiyaaau") extends Animal with Walks {
  override def name: String = _name

  override def makeSound(): String = sound
}
object Lab2 extends App {

  // Here we will test Dog and Cat classes

  val dog1 = Dog("Ceasar")
  val dog2 = Dog("Laika")

  assert(dog1.name == "Ceasar")
  assert(dog2.name == "Laika")

  assert(dog1.makeSound() == "Whooof")
  assert(dog2.makeSound() == "Whooof")

  assert(dog1.walk == "Ceasar is walking")
  assert(dog2.walk == "Laika is walking")

  val cat1 = Cat("Tosha")
  val cat2 = Cat("Chocolate")

  assert(cat1.name == "Tosha")
  assert(cat2.name == "Chocolate")

  assert(cat1.makeSound() == "Miiyaaau")
  assert(cat2.makeSound() == "Miiyaaau")

  assert(cat1.walk == "Tosha is walking")
  assert(cat2.walk == "Chocolate is walking")

}