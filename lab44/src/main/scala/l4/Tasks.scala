package l4
object task extends App {

  case class Film(name: String,
                  yearOfRelease: Int,
                  imdbRating: Double)

  case class Director(firstName: String,
                      lastName: String,
                      yearOfBirth: Int,
                      films: Seq[Film])

  val memento = new Film("Memento", 2000, 8.5)
  val darkKnight = new Film("Dark Knight", 2008, 9.0)
  val inception = new Film("Inception", 2010, 8.8)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = new Film("Unforgiven", 1992, 8.3)
  val granTorino = new Film("Gran Torino", 2008, 8.2)
  val invictus = new Film("Invictus", 2009, 7.4)
  val predator = new Film("Predator", 1987, 7.9)
  val dieHard = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)
  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))
  val someGuy = new Director("Just", "Some Guy", 1990,
    Seq())
  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  def task1(numberOfFilms: Int)={
    directors.filter(p => p.films.length > numberOfFilms)
  }

  def task2(year: Int)={
    directors.filter(p => p.yearOfBirth < year)
  }

  def task3(year: Int,numberOfFilms: Int)={
    directors.filter(p => p.films.length > numberOfFilms && p.yearOfBirth<year)
  }

  def task4(ascending: Boolean = true) ={
    ascending match {
      case true => directors.sortWith(_.yearOfBirth < _.yearOfBirth)
      case _ => directors.sortWith(_.yearOfBirth > _.yearOfBirth)
    }
  }
//  def task5()={
//    for(e<-directors){
//      if(e.lastName=="Nolan" && e.firstName=="Christopher") {
//        println(e.films.map(f => f.name))
//      }
//    }
//  }
  val t5 = nolan.films.map(f => f.name)

  def task6()={
    directors.flatMap(f => f.films.map(d => d.name))
  }
  def task7() = {
    mcTiernan.films.map(f => f.yearOfRelease).min
  }

  def task8() = {
    directors.flatMap(f => f.films).sortWith(_.imdbRating < _.imdbRating)
  }

  def task9() = {
    directors.flatMap(d => d.films.map(f => f.imdbRating)).sum / directors.flatMap(d => d.films).length
  }

  def task10() = {
    println(directors.flatMap(d => d.films.map(f => "Tonight only! " + f.name + " BY " + d.firstName+" "+d.lastName)))
  }

  def task11() = {
    for(e <- directors){
      println(e.films.map(f => f.yearOfRelease).min)
    }
  }

//  def task11() = {
//    directors.flatMap(d => d.films.map(f => f.yearOfRelease)).min
//  }



  println(task1(4))
  println()

  println(task2(1960))
  println()

  println(task3(1960,4))
  println()

  println(task4(false))
  println()

  println(t5)
//  task5()
  println()

  println(task6())
  println()

  println(task7())
  println()

  println(task8())
  println()

  println(task9())
  println()

  task10()
  println()

  println(task11())
  println()
}