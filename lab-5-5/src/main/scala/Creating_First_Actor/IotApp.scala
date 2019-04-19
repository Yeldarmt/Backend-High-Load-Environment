package Creating_First_Actor

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

import scala.io.StdIn

class IotSupervisor extends Actor with ActorLogging{

  override def preStart(): Unit = log.info("Iot Application started")
  //override def preStart(): Unit = println("Iot Application started")
  override def postStop(): Unit = log.info("Iot Application stopped")

  override def receive: Receive = Actor.emptyBehavior
}

//object IotApp {
//  def main(args: Array[String]): Unit = {
//    val system = ActorSystem("iot-system")
//    try{
//      val supervisor = system.actorOf(Props[IotSupervisor],"iot-supervisor")
//      StdIn.readLine();
//    }
//    finally{
//      system.terminate()
//    }
//  }
object IotApp extends App{
    val system = ActorSystem("iot-system")
    try{
      val supervisor = system.actorOf(Props[IotSupervisor],"iot-supervisor")
      StdIn.readLine();
    }
    finally{
      system.terminate()
    }

}
