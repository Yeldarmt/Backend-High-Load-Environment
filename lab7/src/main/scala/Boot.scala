import Football.{DeleteTeam, UpdateTeam}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import models.{Response, Team, Teams}
import akka.http.scaladsl.server.Directives._
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

  val teams = TableQuery[TeamsTable]
  val players = TableQuery[PlayersTable]

  val football = system.actorOf(Football.props(db), "football")

  val route =
    pathPrefix("football") {
      (path("get_teams") & get) {
        get {
          complete {
            (football ? Football.GetTeams).mapTo[Teams]
          }
        }
      }~
        (path("create_team") & post) {
          post {
            entity(as[Team]) { team =>
              complete {
                (football ? Football.CreateTeam(team.name)).mapTo[Future[Int]].flatten.map (x => Response(x))
              }
            }
          }
        }~
        (path("delete_team") & delete){
          entity(as[DeleteTeam]) { deleteTeam: DeleteTeam =>
            complete {
              (football ? Football.DeleteTeam(deleteTeam.name)).mapTo[Future[Int]].flatten.map (x => Response(x))
            }
          }
        }~
        (path("update_team") & put){
          entity(as[UpdateTeam]) { updateTeam: UpdateTeam =>
            complete {
              (football ? Football.UpdateTeam(updateTeam.name, updateTeam.newName)).mapTo[Future[Int]].flatten.map (x => Response(x))
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
