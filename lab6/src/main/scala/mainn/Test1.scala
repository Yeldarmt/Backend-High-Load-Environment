//package mainn
//
//import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
//import mainn.AlmatyBike.{CreateStation, CreatesBike, GetBikeInfoFromStation, getStationsNames}
//
//import scala.collection.mutable.ListBuffer
//
//object Test1 extends App{
//  val system = ActorSystem("ActorSystem")
//  val almatyBike = system.actorOf(AlmatyBike.props(1, 1),"Almaty-Bike")
//  almatyBike ! CreateStation("StationNumber_1",11)
//  almatyBike ! CreateStation("StationNumber_2",12)
//  almatyBike ! CreatesBike(11,14,"FirstBike")
//  almatyBike ! CreatesBike(11,11,"Ural")
//  almatyBike ! GetBikeInfoFromStation(11,11)
//  almatyBike ! getStationsNames(11)
//}
