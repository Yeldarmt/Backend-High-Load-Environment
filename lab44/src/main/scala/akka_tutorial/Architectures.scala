package akka_tutorial
//
//import akka.actor.{ Actor, Props, ActorSystem }
//import scala.io.StdIn
//
//object PrintMyActorRefActor {
//  def props: Props =
//    Props(new PrintMyActorRefActor)
//}
//
//class PrintMyActorRefActor extends Actor {
//  override def receive: Receive = {
//    case "printit" ⇒
//      val secondRef = context.actorOf(Props.empty, "second-actor")
//      println(s"Second: $secondRef")
//  }
//}
//
//object ActorHierarchyExperiments extends App {
//  val system = ActorSystem("testSystem")
//
//  val firstRef = system.actorOf(PrintMyActorRefActor.props, "first-actor")
//  println(s"First: $firstRef")
//  firstRef ! "printit"
//
//  println(">>> Press ENTER to exit <<<")
//  try StdIn.readLine()
//  finally system.terminate()
//}
//
//
//object StartStopActor1 {
//  def props: Props =
//    Props(new StartStopActor1)
//}
//
//class StartStopActor1 extends Actor {
//  override def preStart(): Unit = {
//    println("first started")
//    context.actorOf(StartStopActor2.props, "second")
//  }
//  override def postStop(): Unit = println("first stopped")
//
//  override def receive: Receive = {
//    case "stop" ⇒ context.stop(self)
//  }
//}
//
//object StartStopActor2 {
//  def props: Props =
//    Props(new StartStopActor2)
//}
//
//class StartStopActor2 extends Actor {
//  override def preStart(): Unit = println("second started")
//  override def postStop(): Unit = println("second stopped")
//
//  // Actor.emptyBehavior is a useful placeholder when we don't
//  // want to handle any messages in the actor.
//  override def receive: Receive = Actor.emptyBehavior
//}
//
//class SupervisingActor extends Actor {
//  val child = context.actorOf(SupervisedActor.props, "supervised-actor")
//
//  override def receive: Receive = {
//    case "failChild" ⇒ child ! "fail"
//  }
//}
//
//object SupervisedActor {
//  def props: Props =
//    Props(new SupervisedActor)
//}
//
//class SupervisedActor extends Actor {
//  override def preStart(): Unit = println("supervised actor started")
//  override def postStop(): Unit = println("supervised actor stopped")
//
//  override def receive: Receive = {
//    case "fail" ⇒
//      println("supervised actor fails now")
//      throw new Exception("I failed!")
//  }
//}
//
//class SupervisingActor extends Actor {
//  val child = context.actorOf(SupervisedActor.props, "supervised-actor")
//
//  override def receive: Receive = {
//    case "failChild" ⇒ child ! "fail"
//  }
//}
//
//object SupervisedActor {
//  def props: Props =
//    Props(new SupervisedActor)
//}
//
//class SupervisedActor extends Actor {
//  override def preStart(): Unit = println("supervised actor started")
//  override def postStop(): Unit = println("supervised actor stopped")
//
//  override def receive: Receive = {
//    case "fail" ⇒
//      println("supervised actor fails now")
//      throw new Exception("I failed!")
//  }
//}