//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.util.ArrayList;
import java.io.Serializable;

public class Team implements Serializable {
	private ArrayList<Manager> manager;
	private ArrayList<Player> players;
	private int playerCount;
	private String teamName;
	private String teamColor;

	/**
	 * Constructs and initializes Team object
	 * 
	 * @param teamname
	 * @param teamcolor
	 */
	public Team(String n, String c) {
		this.players = new ArrayList<Player>();
		this.manager = new ArrayList<Manager>();
		this.playerCount = 0;
		this.teamName = n;
		this.teamColor = c;
	} // end constructor Team()

	// public ArrayList<Manager> getManager() {
	//// Manager res = null;
	////
	//// if (manager.isEmpty()) {
	//// System.out.println("This team does not have a manager");
	//// } else {
	//// res = manager.get(0);
	//// }
	////
	//// return res;
	//
	//// return this.manager.get(0);
	// return this.manager;
	// }

	/**
	 * @return manager name A team has a manager list of size 1
	 */
	public Name getManager() {
		if (this.manager.get(0).getName() == null) {
			return null;
		} else {
			return (this.manager.get(0).getName());
		}
	} // end getManager()

	public String getPlayers() {
		String res = "";
		for (int i = 0; i < this.players.size(); i++)
			res += this.players.get(i) + "\n\t";
		return res;
	} // end getPlayers()

	/**
	 * @return int playercount
	 */
	public int getPlayerCount() {
		return this.playerCount;
	} // end getPlayerCount()

	/**
	 * @return String teamcolor
	 */
	public String getColor() {
		return this.teamColor;
	} // end getColor()

	/**
	 * @return String teamname
	 */
	public String getTeamName() {
		return this.teamName;
	} // end getTeamName()

	/**
	 * @param String
	 *            newTeamName
	 */
	public void setTeamName(String newTeamName) {
		this.teamName = newTeamName;
	} // end setTeamName()

	/**
	 * Assign a manager to the team. A team has a manager list of size 1
	 * 
	 * @param manager
	 *            a team has a manager list of size 1
	 */
	public void addManager(Manager m) {
		// if (this.manager.isEmpty()) {
		// this.manager.add(0, m); //there should only be one manager in this list
		// } else {
		// System.out.print("This team already has a manager");
		// }
		this.manager.add(m);
	} // end addManager()

	/**
	 * @param int
	 *            index
	 */
	public void removeManager(int index) {
		// if (!this.manager.isEmpty()) {
		// this.manager.remove(index);
		// } else {
		// System.out.print("This team already has no manager");
		// }
		this.manager.remove(index);
	} // end removeManager()

	/**
	 * This function uses the Player class to add a player to a team
	 * 
	 * @param Player
	 *            newPlayer
	 */
	public void addPlayer(Player newPlayer) {
		this.players.add(newPlayer);
		this.playerCount++;
	} // end addPlayer

	/**
	 * @param int
	 *            index
	 */
	public void removePlayer(int index) {
		this.players.remove(index);
		this.playerCount--;
	} // end removePlayer

	public String toString() {
		return this.teamName + " " + this.teamColor + " " + this.playerCount;
	} // end toString()
}
