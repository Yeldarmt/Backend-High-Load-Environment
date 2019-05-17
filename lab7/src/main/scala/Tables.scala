import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ProvenShape, Tag}
import models.{Player, Team}

class TeamsTable(tag: Tag) extends Table[Team](tag, "TEAMS") {
  def TeamId: Rep[Int] = column[Int]("TEAM_ID", O.PrimaryKey, O.AutoInc)
  def TeamName: Rep[String] = column[String]("TEAM_NAME")

  def * : ProvenShape[Team] = (TeamId.?, TeamName) <> (Team.tupled, Team.unapply)
}


class PlayersTable(tag: Tag) extends Table[Player](tag, "PLAYERS") {
  def playerId: Rep[Int] = column[Int]("PLAYER_ID", O.PrimaryKey, O.AutoInc)
  def playerName: Rep[String] = column[String]("PLAYER_NAME")
  def playerSurname: Rep[String] = column[String]("PLAYER_SURNAME")
  def playerNumber: Rep[Int] = column[Int]("PLAYER_NUMBER")
  def playerPosition: Rep[String] = column[String]("PLAYER_POSITION")
  def playerAge: Rep[Int] = column[Int]("PLAYER_AGE")

  def * : ProvenShape[Player] = (playerId.?, playerName, playerSurname, playerNumber, playerPosition,playerAge) <> (Player.tupled, Player.unapply)
}