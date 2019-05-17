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
  case class DeleteTeam(name: String)
  case object GetTeams
  case class UpdateTeam(name: String, newName: String)
  case class Txt(txt: String)

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

    case DeleteTeam(newName) =>
      sender() ! db.run(
        teamsTable.filter(_.TeamName==newName).delete
      )

    case UpdateTeam(name,newName) =>
      db.run(teamsTable+=Team(name=name))
      sender() ! db.run(
        teamsTable+=Team(name=newName)
      )
  }
}

//        teamsTable-=Team(name=newName)
