package query_device_groups

class Example_Almatybike {

}
/*package lab_6

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

import scala.collection.mutable.ListBuffer

//object AlmatyBike{
//  def props(bikeID: Int, stationID: Int): Props = Props(new AlmatyBike(bikeID, stationID))
//}
class AlmatyBike extends Actor with ActorLogging {

  val bikeStation = context.actorOf(Props[BikeStation], "Bike-Station")

  var stations = Map.empty[Int, ActorRef]

  override def receive: Receive = {
    case GetBikeInfoFromStation(bId, sId) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! GetBikeInfo(bId)
      }
    case CreateStation(name, sId) =>
      log.info(s"Creating station $name with id: $sId")
      val bikeStation = context.actorOf(Props[BikeStation], "Bike-Station")
      stations += (sId -> bikeStation)
      sender ! s"$name created succesfully"
    case CreatesBike(sId, bId, name) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! CreateBike(bId, name)
      }
  }
}
class BikeStation extends Actor with ActorLogging{

  val bike = context.actorOf(Props[Bike],"Bike")

  var bikes = Map.empty[Int,ActorRef]

  override def receive: Receive = {
    case GetBikeInfo(bId) =>
      bikes.foreach{b =>
        if(b._1 == bId)
          b._2 ! GetInfo
      }
    case CreateBike(bId,name) =>
      log.info(s"Creating bike $name with id: $bId")
      val bike = context.actorOf(Props[Bike],"Bike")
      bikes += (bId -> bike)
      sender ! s"$name bike created succesfully"
  }

}
class Bike extends Actor with ActorLogging{

  override def receive: Receive = {
    case g : GetInfo => log.info(s"Name: bikegoi")
  }

}
case class GetBikeInfoFromStation(bikeID: Int, stationId: Int)

case class GetBikeInfo(bikeID: Int)

case class GetInfo(bId: Int)

case class CreateStation(name: String,stationId: Int)

case class CreatesBike(stationId: Int, bikeId: Int,name: String)

case class CreateBike(bikeId: Int,name: String)

object Test1 extends App{
  val system = ActorSystem("ActorSystem")
  val almatyBike = system.actorOf(Props[AlmatyBike],"Almaty-Bike")
  //almatyBike ! lab_6.CreateStation("Station1",13)
  almatyBike ! CreatesBike(13,1,"BikeNumber1")
}
*/

