package akka_architecture

import akka.actor.{Actor, ActorSystem, Props}

class SupervisingActor extends Actor{
  val child = context.actorOf(Props[SupervisedActor],"super-vised-actor")
  override def receive: Receive ={
    case "failChild"=> child ! "fail"
  }
}
class SupervisedActor extends Actor{

  override def preStart(): Unit = println("supervised actor started")
  override def postStop(): Unit = println("supervised actor stopped")
  override def receive: Receive = {
    case "fail" =>
      println("supervised actor fails now")
      throw new Exception("I failed!")
  }
}

object Failure_Example extends App{
  val system = ActorSystem("testSystem")
  val supervisingActor = system.actorOf(Props[SupervisingActor],"super-vising-actor")
  supervisingActor ! "failChild"
}
