//package query_device_groups
//
//import akka.actor.{Actor, ActorLogging, ActorRef, Props, Terminated}
//import device_actors.{Device, DeviceGroup, RequestAllTemperatures}
//
//import scala.concurrent.duration.FiniteDuration
//
//object DeviceGroupQuery {
//  case object CollectionTimeout
//
//  def props(
//             actorToDeviceId: Map[ActorRef, String],
//             requestId:       Long,
//             requester:       ActorRef,
//             timeout:         FiniteDuration
//           ): Props = {
//    Props(new DeviceGroupQuery(actorToDeviceId, requestId, requester, timeout))
//  }
//}
//
//class DeviceGroupQuery(
//                        actorToDeviceId: Map[ActorRef, String],
//                        requestId:       Long,
//                        requester:       ActorRef,
//                        timeout:         FiniteDuration
//                      ) extends Actor with ActorLogging {
//  import DeviceGroupQuery._
//  import context.dispatcher
//  val queryTimeoutTimer = context.system.scheduler.scheduleOnce(timeout, self, CollectionTimeout)
//
//  override def preStart(): Unit = {
//    actorToDeviceId.keysIterator.foreach { deviceActor ⇒
//      context.watch(deviceActor)
//      deviceActor ! Device.ReadTemperature(0)
//    }
//  }
//
//  override def postStop(): Unit = {
//    queryTimeoutTimer.cancel()
//  }
//
//}
//
//object DeviceGroupQuery {
//  case object CollectionTimeout
//
//  def props(
//             actorToDeviceId: Map[ActorRef, String],
//             requestId:       Long,
//             requester:       ActorRef,
//             timeout:         FiniteDuration
//           ): Props = {
//    Props(new DeviceGroupQuery(actorToDeviceId, requestId, requester, timeout))
//  }
//}
//
//class DeviceGroupQuery(
//                        actorToDeviceId: Map[ActorRef, String],
//                        requestId:       Long,
//                        requester:       ActorRef,
//                        timeout:         FiniteDuration
//                      ) extends Actor with ActorLogging {
//  import DeviceGroupQuery._
//  import context.dispatcher
//  val queryTimeoutTimer = context.system.scheduler.scheduleOnce(timeout, self, CollectionTimeout)
//
//  override def preStart(): Unit = {
//    actorToDeviceId.keysIterator.foreach { deviceActor ⇒
//      context.watch(deviceActor)
//      deviceActor ! Device.ReadTemperature(0)
//    }
//  }
//
//  override def postStop(): Unit = {
//    queryTimeoutTimer.cancel()
//  }
//
//  override def receive: Receive =
//    waitingForReplies(
//      Map.empty,
//      actorToDeviceId.keySet
//    )
//
//  def waitingForReplies(
//                         repliesSoFar: Map[String, DeviceGroup.TemperatureReading],
//                         stillWaiting: Set[ActorRef]
//                       ): Receive = {
//    case Device.RespondTemperature(0, valueOption) ⇒
//      val deviceActor = sender()
//      val reading = valueOption match {
//        case Some(value) ⇒ DeviceGroup.Temperature(value)
//        case None        ⇒ DeviceGroup.TemperatureNotAvailable
//      }
//      receivedResponse(deviceActor, reading, stillWaiting, repliesSoFar)
//
//    case Terminated(deviceActor) ⇒
//      receivedResponse(deviceActor, DeviceGroup.DeviceNotAvailable, stillWaiting, repliesSoFar)
//
//    case CollectionTimeout ⇒
//      val timedOutReplies =
//        stillWaiting.map { deviceActor ⇒
//          val deviceId = actorToDeviceId(deviceActor)
//          deviceId -> DeviceGroup.DeviceTimedOut
//        }
//      requester ! DeviceGroup.RespondAllTemperatures(requestId, repliesSoFar ++ timedOutReplies)
//      context.stop(self)
//  }
//
//  def receivedResponse(
//                        deviceActor:  ActorRef,
//                        reading:      DeviceGroup.TemperatureReading,
//                        stillWaiting: Set[ActorRef],
//                        repliesSoFar: Map[String, DeviceGroup.TemperatureReading]
//                      ): Unit = {
//    context.unwatch(deviceActor)
//    val deviceId = actorToDeviceId(deviceActor)
//    val newStillWaiting = stillWaiting - deviceActor
//
//    val newRepliesSoFar = repliesSoFar + (deviceId -> reading)
//    if (newStillWaiting.isEmpty) {
//      requester ! DeviceGroup.RespondAllTemperatures(requestId, newRepliesSoFar)
//      context.stop(self)
//    } else {
//      context.become(waitingForReplies(newRepliesSoFar, newStillWaiting))
//    }
//  }
//
//}
//
//class DeviceGroup(groupId: String) extends Actor with ActorLogging {
//  var deviceIdToActor = Map.empty[String, ActorRef]
//  var actorToDeviceId = Map.empty[ActorRef, String]
//  var nextCollectionId = 0L
//
//  override def preStart(): Unit = log.info("DeviceGroup {} started", groupId)
//
//  override def postStop(): Unit = log.info("DeviceGroup {} stopped", groupId)
//
//  override def receive: Receive = {
//    // ... other cases omitted
//
//    case RequestAllTemperatures(requestId) ⇒
//      context.actorOf(DeviceGroupQuery.props(
//        actorToDeviceId = actorToDeviceId,
//        requestId = requestId,
//        requester = sender(),
//        3.seconds
//      ))
//  }
//}
