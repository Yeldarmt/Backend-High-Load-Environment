import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import models.{Response, Team, Teams}
import akka.http.scaladsl.server.Directives.{concat, pathPrefix}
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http
import org.slf4j.LoggerFactory
import slick.jdbc.MySQLProfile
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.pattern.ask

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Boot extends App with JsonSupport {

  val log = LoggerFactory.getLogger("Boot")

  implicit val system = ActorSystem()

  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher

  implicit val timeout = Timeout(30.seconds)

  val db: MySQLProfile.backend.Database = Database.forConfig("mysql")

  val heroes = TableQuery[TeamsTable]
  val items = TableQuery[PlayersTable]

  val football = system.actorOf(Football.props(db), "football")

  val route =
    pathPrefix("football") {
      path("teams") {
        get {
          complete {
            (football ? Football.GetTeams).mapTo[Teams]
          }
        }
      } ~
        path("hero") {
          post {
            entity(as[Team]) { team =>
              complete {
                (football ? Football.CreateTeam(team.name)).mapTo[Future[Int]].flatten.map (x => Response(x))
              }
            }
          }
        }
    }

  val config = ConfigFactory.load()

  val shouldCreate = config.getBoolean("create-schema")

  if (shouldCreate) {
    try {
      Await.result(db.run(DBIO.seq(
        teams.schema.create,
        players.schema.create,

        teams.result.map(println),
        players.result.map(println)
      )), Duration.Inf)
    }
  }


  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  log.info("Listening on port 8080...")

}
