package mainn

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import mainn.AlmatyBike._
import mainn.Bike.ResponseFromBike
import mainn.BikeStation.{CreateBike, GetBikeInfo, MsgFromBikeStation}

object AlmatyBike{
  def props(bikeID: Int, stationID: Int): Props =
    Props(new AlmatyBike(bikeID, stationID))

  case class GetBikeInfoFromStation(bikeID: Int, stationId: Int)

  case class CreatesBike(stationId: Int, bikeId: Int,name: String)

  case class getStationsNames(sId: Int)

  case class deleteStation(stationID: Int)

  case class CreateStation(name: String,stationId: Int)

  case class Txt(txt: String)

  case class MessageDelStation(txt: String)

  case class MsgFromGetsn(s: String)

}

class AlmatyBike(bikeId: Int, stationId: Int) extends Actor with ActorLogging {


  val bikeStation = context.actorOf(BikeStation.props(1), "Bike-Station")

  var stations = Map.empty[Int, ActorRef]

  override def receive: Receive = {
    case GetBikeInfoFromStation(bId, sId) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! GetBikeInfo(bId)
        context.become(waitingResponseFromBikeStation(sender()))
      }
    case CreateStation(name, sId) =>
      log.info(s"Creating station $name with id: $sId")
      //val bikeStationn = context.actorOf(BikeStation.props(1), "Bike-Station")
      stations += (sId -> bikeStation)
      sender() ! Txt(s"$name with id:$sId created succesfully")
      println(s"$name created succesfully")
    case CreatesBike(sId, bId, name) =>
      stations.foreach { s =>
        if (s._1 == sId)
          s._2 ! CreateBike(bId, name)
        context.become(waitingResponseCreateBike(sender()))
      }
//    case getStationsNames =>
//      stations.foreach{s =>
//      sender() ! MsgFromGetsn(s"Sttation ID: $s._1 ")
//      }
    case deleteStation(sid) =>
      stations-=sid
      println(s"Station with id $sid deleted")
      sender() ! Txt(s"Station with id : $sid deleted")

  }
  def waitingResponseFromBikeStation(senderRef: ActorRef): Receive = {
    //case msg @ ResponseFromBike(s: String) =>
    case msg @ Txt(s: String) =>
      log.info("Waiting responce from BikeStation")
      senderRef ! msg
      context.become(receive)
  }
  def waitingResponseCreateBike(senderRef: ActorRef): Receive = {
    case msg @ MsgFromBikeStation(t: String) =>
      log.info("Waiting responce from CreateBike")
      senderRef ! msg
      context.become(receive)

  }

}
