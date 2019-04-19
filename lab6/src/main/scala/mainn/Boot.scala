package mainn

import akka.actor.{ActorRef, ActorSystem}
import mainn.JsonSupport
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import mainn.AlmatyBike.{CreateStation, CreatesBike, GetBikeInfoFromStation, deleteStation}
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.concurrent.duration._

object Boot extends App with JsonSupport {

  val log = LoggerFactory.getLogger("Boot")
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(30.seconds)

  val almatyBike = system.actorOf(AlmatyBike.props(1, 1),"Almaty-Bike")

  val route = {
    pathPrefix("bikesystem") {
      (path("getinfo") & get){

        entity(as[GetBikeInfoFromStation]) { getBikeInfoFromStation: GetBikeInfoFromStation =>
          complete {
            (almatyBike ? AlmatyBike.GetBikeInfoFromStation(getBikeInfoFromStation.bikeID, getBikeInfoFromStation.stationId)).mapTo[AlmatyBike.Txt]
          }
        }
      } ~
        (path("createstation") & post){
          entity(as[CreateStation]) { createStation: CreateStation =>
            complete {
              (almatyBike ? AlmatyBike.CreateStation(createStation.name, createStation.stationId)).mapTo[AlmatyBike.Txt]
            }
          }
        } ~
        (path("createbike") & post){
          entity(as[CreatesBike]) { createsBike: CreatesBike =>
            complete {
              (almatyBike ? AlmatyBike.CreatesBike(createsBike.stationId,createsBike.bikeId,createsBike.name)).mapTo[BikeStation.MsgFromBikeStation]
            }
          }
        }~
        (path("deletestation") & delete){
          entity(as[deleteStation]) { deletestation: deleteStation =>
            complete {
              (almatyBike ? AlmatyBike.deleteStation( deletestation.stationID)).mapTo[AlmatyBike.Txt]
            }
          }
        }
    }

  }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  log.info("Listening on port 8080...")

}
