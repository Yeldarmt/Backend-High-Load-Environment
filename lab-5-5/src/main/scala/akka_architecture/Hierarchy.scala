package akka_architecture

import akka.actor.{Actor, ActorSystem, Props}

//object StartStopActor1 {
//  def props: Props =
//    Props(new StartStopActor1)
//}
class StartStopActor1 extends Actor{
  override def preStart(): Unit ={
    println("first started")
    context.actorOf(Props[StartStopActor2] ,"second")
  }

  override def postStop(): Unit = println("first stopped")

  override def receive: Receive = {
//    case "stop" => context.stop(self)
    case "stop" => context.stop(self)
  }
}
//object StartStopActor2{
//  def props: Props =
//    Props(new StartStopActor2)
//}
class StartStopActor2 extends Actor{
  override def preStart(): Unit = println("second started")
  override def postStop(): Unit = println("second stopped")
  override def receive: Receive = Actor.emptyBehavior
}

object Hierarchy extends App{
  val system = ActorSystem("ActorSystem")
  val first = system.actorOf(Props[StartStopActor1], "first")
  first ! "stop"
}
