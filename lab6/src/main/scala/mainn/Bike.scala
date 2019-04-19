package mainn

import akka.actor.{Actor, ActorLogging, Props}
import mainn.AlmatyBike.Txt
import mainn.Bike.{GetInfo, ResponseFromBike}

object Bike{
  def props(bikeID: Int,bikename: String): Props =
    Props(new Bike(bikeID,bikename))
  case class GetInfo(bId: Int)
  case class ResponseFromBike(txt: String)
}
class Bike(id: Int,name: String) extends Actor with ActorLogging{

  override def receive: Receive = {
    case GetInfo(id) =>
      log.info(s"Name: Belik with id: $id")
      sender() ! Txt(s"Name: Belik with id: $id")
    //sender() ! ResponseFromBike(s"Name: Belik with id: $id")
  }

}
