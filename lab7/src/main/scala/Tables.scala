import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ProvenShape, Tag}
import models.{Player, Team}

class TeamsTable(tag: Tag) extends Table[Team](tag, "TEAMS") {
  // This is the primary key column:
  def TeamId: Rep[Int] = column[Int]("TEAM_ID", O.PrimaryKey, O.AutoInc)
  def TeamName: Rep[String] = column[String]("TEAM_NAME")

  def * : ProvenShape[Team] = (TeamId.?, TeamName) <> (Team.tupled, Team.unapply)
}

class PlayersTable(tag: Tag) extends Table[Player](tag, "PLAYERS") {
  // This is the primary key column:
  def playerId: Rep[Int] = column[Int]("PLAYER_ID", O.PrimaryKey, O.AutoInc)
  def playerName: Rep[String] = column[String]("PLAYER_POSITION")

  def * : ProvenShape[Player] = (playerId.?, playerName) <> (Player.tupled, Player.unapply)
}