package lab_6

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

import scala.collection.mutable.ListBuffer

object AlmatyBike{
  def props(bikeID: Int, stationID: Int): Props =
    Props(new AlmatyBike(bikeID, stationID))
}

class AlmatyBike(bikeId: Int, stationId: Int) extends Actor with ActorLogging {

  val bikeStation = context.actorOf(BikeStation.props(1), "Bike-Station")

  var stations = Map.empty[Int, ActorRef]

  override def receive: Receive = {
    case GetBikeInfoFromStation(bId, sId) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! GetBikeInfo(bId)
      }
    case CreateStation(name, sId) =>
      log.info(s"Creating station $name with id: $sId")
      //val bikeStationn = context.actorOf(BikeStation.props(1), "Bike-Station")
      stations += (sId -> bikeStation)
      sender ! s"$name created succesfully"
      println(s"$name created succesfully")
    case CreatesBike(sId, bId, name) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! CreateBike(bId, name)
      }
    case getStationsNames =>
      stations.foreach{s => log.info(s"Station ID: $s._1")}
  }
}

object BikeStation{
  def props(bikeStationID: Int):Props =
    Props(new BikeStation(bikeStationID))
}
class BikeStation(id: Int) extends Actor with ActorLogging{

  val bike = context.actorOf(Bike.props(1,"bike_name"),"Bike")

  var bikes = Map.empty[Int,ActorRef]

  override def receive: Receive = {
    case GetBikeInfo(bId) =>
      bikes.foreach{b =>
        if(b._1 == bId)
          b._2 ! GetInfo(b._1)
      }
    case CreateBike(bId,name) =>
      log.info(s"Creating bike $name with id: $bId")
      //val bike = context.actorOf(Bike.props(1,"bike_name"),"Bike")
      bikes += (bId -> bike)
      sender ! s"$name bike created succesfully"
      println(s"$name bike created succesfully")
  }

}

object Bike{
  def props(bikeID: Int,bikename: String): Props =
    Props(new Bike(bikeID,bikename))
}
class Bike(id: Int,name: String) extends Actor with ActorLogging{

  override def receive: Receive = {
    case GetInfo(id) => log.info(s"Name: Belik with id: $id")
  }

}
case class GetBikeInfoFromStation(bikeID: Int, stationId: Int)

case class GetBikeInfo(bikeID: Int)

case class GetInfo(bId: Int)

case class CreateStation(name: String,stationId: Int)

case class CreatesBike(stationId: Int, bikeId: Int,name: String)

case class CreateBike(bikeId: Int,name: String)

case object getStationsNames

object Test1 extends App{
  val system = ActorSystem("ActorSystem")
  val almatyBike = system.actorOf(AlmatyBike.props(1, 1),"Almaty-Bike")
  almatyBike ! CreateStation("StationNumber_1",11)
  almatyBike ! CreateStation("StationNumber_2",12)
  almatyBike ! CreatesBike(11,14,"FirstBike")
  almatyBike ! CreatesBike(11,11,"Ural")
  almatyBike ! GetBikeInfoFromStation(11,11)
  almatyBike ! getStationsNames
}