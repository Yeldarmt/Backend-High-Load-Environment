import Football._
import models.{Player, Response, Team, Teams}
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

trait JsonSupport {
  implicit val teamFormat: RootJsonFormat[Team]=jsonFormat2(Team)
  implicit val playerFormat: RootJsonFormat[Player]=jsonFormat6(Player)
  implicit val teamsFormat: RootJsonFormat[Teams]=jsonFormat1(Teams)
  implicit val c: RootJsonFormat[CreateTeam]=jsonFormat1(CreateTeam)
  implicit val d: RootJsonFormat[DeleteTeam]=jsonFormat1(DeleteTeam)
  implicit val u: RootJsonFormat[UpdateTeam]=jsonFormat2(UpdateTeam)
  implicit val responseFormat: RootJsonFormat[Response]=jsonFormat1(Response)
  implicit val txt: RootJsonFormat[Txt]=jsonFormat1(Txt)

}
