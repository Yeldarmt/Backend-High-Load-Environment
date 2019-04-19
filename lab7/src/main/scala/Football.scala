import akka.actor.{Actor, ActorLogging, Props}
import models.{Team, Teams}
import slick.jdbc.MySQLProfile
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Football{
  case class CreateTeam(name: String)
  case object GetTeams

  def props(db: MySQLProfile.backend.Database)=Props(new Football(db))
}
class Football(db: MySQLProfile.backend.Database) extends Actor with ActorLogging{
  import Football._

  val teamsTable = TableQuery[TeamsTable]
  val playersTable = TableQuery[PlayersTable]

  val teams: Seq[Team] = Await.result(db.run(teamsTable.result), 5.seconds)
  val players = Await.result(db.run(playersTable.result), 5.seconds)
  override def receive: Receive = {
    case CreateTeam(newName) =>
      sender() ! db.run(
        teamsTable += Team(name = newName)
      )
    case GetTeams =>
      sender() ! Teams(teams)
  }

}
