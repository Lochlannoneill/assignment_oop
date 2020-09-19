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
import model.Manager;

/**
 * @author Lochlann Manages interactions with the gui to the manager database
 */
public class ManagerController {

	private static final String DATABASENAME = "managerList";
	private static Connection connection = DBConnection.getConnection(DATABASENAME);

	// ---------------------------------------------------------------------------------
	// -------------------------------- addManager()
	// ---------------------------------------------------------------------------------
	/**
	 * Add manager to the managerlist database
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
	 * @param String
	 *            dob
	 * @param int
	 *            rating
	 */
	public static void addManager(String first, String middle, String last, int phone, String email, String dob,
			int rating) {
		try {
			String query;
			Statement statement = connection.createStatement();

//			query = "CREATE TABLE IF NOT EXISTS " + DATABASENAME
//					+ " (first varchar(32) NOT NULL, middle varchar(32) NOT NULL, last varchar(32) NOT NULL, phone int NOT NULL UNIQUE, email varchar(64) NOT NULL UNIQUE, dob date NOT NULL, rating int, PRIMARY KEY (phone))";
//			statement.executeUpdate(query);

			query = "INSERT INTO " + DATABASENAME
					+ "(`first`, `middle`, `last`, `phone`, `email`, `dob`, `rating`) VALUES ('" + first + "','"
					+ middle + "','" + last + "','" + phone + "','" + email + "','" + dob + "','" + rating + "')";
			statement.executeUpdate(query);

			statement.close();
			System.out.println(first + middle + last + " added to " + DATABASENAME + " table");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end addPerson()

	// ---------------------------------------------------------------------------------
	// -------------------------------- removeManager()
	// ---------------------------------------------------------------------------------
	/**
	 * Removes manager from the managerlist database
	 * 
	 * @param int
	 *            phone
	 */
	public static void removeManager(int phone) {
		try {
			String query = "DELETE FROM " + DATABASENAME + " WHERE `phone` = '" + phone + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println("Manager removed from " + DATABASENAME + " table");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		}
	} // end removePlayer()

	// ---------------------------------------------------------------------------------
	// -------------------------------- getManagers()
	// ---------------------------------------------------------------------------------
	/**
	 * Returns ArrayList of type Manager
	 * 
	 * @return ArrayList<Manager> returnlist
	 */
	public static ArrayList<Manager> getManagers() {
		ArrayList<Manager> returnlist = new ArrayList<Manager>();
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
				String currentDOB = resultset.getString("DOB");
				int currentRating = resultset.getInt("rating");

				// create a new player object for each database query result
				Manager currentManager = new Manager(currentFirst, currentMiddle, currentLast, currentPhone,
						currentEmail, currentDOB, currentRating);
				returnlist.add(currentManager);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnlist;
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