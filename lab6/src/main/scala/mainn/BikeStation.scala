package mainn

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import mainn.AlmatyBike.{ Txt}
import mainn.Bike.{GetInfo, ResponseFromBike}
import mainn.BikeStation.{CreateBike, GetBikeInfo, MsgFromBikeStation}

object BikeStation{
  def props(bikeStationID: Int):Props =
    Props(new BikeStation(bikeStationID))

  case class GetBikeInfo(bikeID: Int)
  case class CreateBike(bikeId: Int,name: String)
  case class MsgFromBikeStation(txt: String)
}
class BikeStation(id: Int) extends Actor with ActorLogging{

  val bike = context.actorOf(Bike.props(1,"bike_name"),"Bike")

  var bikes = Map.empty[Int,ActorRef]

  override def receive: Receive = {
    case GetBikeInfo(bId) =>
      bikes.foreach{b =>
        if(b._1 == bId)
          b._2 ! GetInfo(b._1)
        context.become(waitingResponseFromBike(sender()))
      }
    case CreateBike(bId,name) =>
      log.info(s"Creating bike $name with id: $bId")
      //val bike = context.actorOf(Bike.props(1,"bike_name"),"Bike")
      bikes += (bId -> bike)
      sender() ! MsgFromBikeStation(s"$name bikke with id: $bId created succesfully")
      println(s"$name bike created succesfully")

  }

  def waitingResponseFromBike(senderRef: ActorRef): Receive = {
    //case msg @ ResponseFromBike(txt: String) =>
    case msg @ Txt(txt: String) =>
      log.info("Waiting responce from Bike")
      senderRef ! msg
      context.become(receive)


  }

}
