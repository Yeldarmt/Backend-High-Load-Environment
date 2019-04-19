import Football.Txt
import models.{Response, Team, Teams}
import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._

trait JsonSupport {
  implicit val teamFormat: RootJsonFormat[Team]=jsonFormat2(Team)
  implicit val teamsFormat: RootJsonFormat[Teams]=jsonFormat1(Teams)
  implicit val responseFormat: RootJsonFormat[Response]=jsonFormat1(Response)
  implicit val txt: RootJsonFormat[Txt]=jsonFormat1(Txt)

}
