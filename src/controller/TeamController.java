//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Player;
import model.Team;

public class TeamController {

	private static final String DATABASENAME = "teamlist";
	private static Connection connection = DBConnection.getConnection(DATABASENAME);

	// ---------------------------------------------------------------------------------
	// -------------------------------- addTeam()
	// ---------------------------------------------------------------------------------
	/**
	 * Add team to the teamlist database
	 * 
	 * @param String
	 *            teamName
	 * @param String
	 *            teamColor
	 */
	public static void addTeam(String teamName, String teamColor) {
		// the team starts with 0 players
		try {
			String query;
			Statement statement = connection.createStatement();

//			query = "CREATE TABLE IF NOT EXISTS" + DATABASENAME
//					+ " (teamname varchar(32) NOT NULL UNIQUE,teamcolor varchar(32) NOT NULL,PRIMARY KEY (teamname));";
//			statement.executeUpdate(query);

			query = "CREATE TABLE IF NOT EXISTS " + teamName
					+ " (first varchar(32) NOT NULL, last varchar(32) NOT NULL, phone INT NOT NULL UNIQUE, PRIMARY KEY (phone))";
			statement.executeUpdate(query);
			System.out.println(teamName + " team table created");

			query = "INSERT INTO " + DATABASENAME + " (`teamname`, `teamcolor`) VALUES ('" + teamName + "','"
					+ teamColor + "')";
			statement.executeUpdate(query);
			System.out.println("Team added to " + DATABASENAME + " table");

			statement.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end addTeam()

	// ---------------------------------------------------------------------------------
	// -------------------------------- removeTeam()
	// ---------------------------------------------------------------------------------
	/**
	 * Removes team from the teamlist database
	 * 
	 * @param teamName
	 */
	public static void removeTeam(String teamName) {
		try {
			String query;
			Statement statement = connection.createStatement();
			query = "DELETE FROM " + DATABASENAME + " WHERE `teamName` = '" + teamName + "'";
			statement.executeUpdate(query);

			query = "DROP TABLE " + teamName;
			statement.executeUpdate(query);

			statement.close();
			System.out.println("Team removed from " + DATABASENAME + " table");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end removePlayer()

	// ---------------------------------------------------------------------------------
	// -------------------------------- getTeam()
	// ---------------------------------------------------------------------------------
	/**
	 * Returns ArrayList of type Team
	 * 
	 * @return ArrayList<Team> teamList
	 */
	public static ArrayList<Team> getTeam() {
		ArrayList<Team> teamList = new ArrayList<Team>();
		try {
			String query = "SELECT * FROM " + DATABASENAME + "";
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				// create variables to be used as arguments for object creation
				String currentTeamName = resultset.getString("teamName");
				String currentTeamColor = resultset.getString("teamColor");
				// int currentTeamPlayerCount = resultset.getInt("teamPlayerCount");

				// create a new player object for each database query result
				Team currentTeam = new Team(currentTeamName, currentTeamColor);
				teamList.add(currentTeam);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return teamList;
	} // end getPlayers()

	// ---------------------------------------------------------------------------------
	// -------------------------------- searchTeam()
	// ---------------------------------------------------------------------------------
	/**
	 * 
	 * @param String
	 *            teamName
	 * @return ArrayList<Player> playerList
	 */
	public static ArrayList<Player> searchTeam(String teamName) {
		ArrayList<Player> playerList = new ArrayList<Player>();
		try {
			String query = "SELECT * FROM " + teamName;
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				// create variables to be used as arguments for object creation
				String currentFirst = resultset.getString("first");
				String currentMiddle = resultset.getString("last");
				String currentLast = resultset.getString("phone");
				int currentPhone = resultset.getInt("phone");
				String currentEmail = resultset.getString("email");
				int currentGoals = resultset.getInt("goals");
				Boolean currentIsGoalie = resultset.getBoolean("isGoalie");

				// create a new player object for each database query result
				Player currentPlayer = new Player(currentFirst, currentMiddle, currentLast, currentPhone, currentEmail,
						currentGoals, currentIsGoalie);
				playerList.add(currentPlayer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return playerList;
	} // end getPlayers()

	// ---------------------------------------------------------------------------------
	// -------------------------------- close()
	// ---------------------------------------------------------------------------------
	public static void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	} // end close()

} // end Class PlayerController