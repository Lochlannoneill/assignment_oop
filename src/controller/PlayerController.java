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

public class PlayerController {

	private static final String DATABASENAME = "playerlist";
	private static Connection connection = DBConnection.getConnection(DATABASENAME);

	// ---------------------------------------------------------------------------------
	// -------------------------------- addPlayer()
	// ---------------------------------------------------------------------------------
	/**
	 * Add player to the playerlist database
	 * 
	 * @param String
	 *            first
	 * @param String
	 *            middle
	 * @param String
	 *            last
	 * @param int
	 *            phone
	 * @param String
	 *            email
	 * @param int
	 *            goals
	 * @param boolean
	 *            isGoalie
	 */
	public static void addPlayer(String first, String middle, String last, int phone, String email, int goals,
			boolean isGoalie) {
		// msql boolean uses 1/0 instead of true/false
		int isGoalieInteger = isGoalie ? 1 : 0;

		try {
			String query;
			Statement statement = connection.createStatement();

//			query = "CREATE TABLE IF NOT EXISTS " + DATABASENAME
//					+ "(first varchar(32) NOT NULL,middle varchar(32) NOT NULL,last varchar(32) NOT NULL,phone INT NOT NULL UNIQUE,email varchar(64) NOT NULL UNIQUE,goals INT,isgoalie BOOLEAN NOT NULL);";
//			statement.executeUpdate(query);

			query = "INSERT INTO " + DATABASENAME
					+ " (`first`, `middle`, `last`, `phone`, `email`, `goals`, `isGoalie`) VALUES ('" + first + "','"
					+ middle + "','" + last + "','" + phone + "','" + email + "','" + goals + "','" + isGoalieInteger
					+ "')";
			statement.executeUpdate(query);

			statement.close();
			System.out.println("Player added to " + DATABASENAME + " table");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end addPerson()

	// ---------------------------------------------------------------------------------
	// -------------------------------- removePlayer()
	// ---------------------------------------------------------------------------------
	/**
	 * Removes player from the playerlist database
	 * 
	 * @param int
	 *            phone
	 */
	public static void removePlayer(int phone) {
		try {
			String query = "DELETE FROM  " + DATABASENAME + " WHERE `phone` = '" + phone + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println("Player removed from " + DATABASENAME + " table");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end removePlayer()

	// ---------------------------------------------------------------------------------
	// -------------------------------- getPlayers()
	// ---------------------------------------------------------------------------------
	/**
	 * Returns ArrayList of type Player
	 * 
	 * @return ArrayList<Player> playerList
	 */
	public static ArrayList<Player> getPlayers() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		try {
			String query = "SELECT * FROM " + DATABASENAME;
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				// create variables to be used as arguments for object creation
				String currentFirst = resultset.getString("first");
				String currentMiddle = resultset.getString("middle");
				String currentLast = resultset.getString("last");
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
	// -------------------------------- searchPlayer()
	// ---------------------------------------------------------------------------------
	/**
	 * Removes manager from the managerlist database Search database for player
	 * where parameters match database columns
	 * 
	 * @param String
	 *            first
	 * @param String
	 *            middle
	 * @param String
	 *            last
	 */
	public static void searchPlayer(String first, String middle, String last) {
		try {
			String query = "SELECT " + DATABASENAME + " FROM `playerlist` WHERE `first` = '" + first
					+ "' AND `middle` = '" + middle + "' and `last` = '" + last + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end removePlayer()

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