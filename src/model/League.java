//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class League implements Serializable {
	private ArrayList<Team> teams;
	private String leagueName;

	public League(String ln) {
		this.teams = new ArrayList<Team>();
		this.leagueName = ln;
	} // end League constructor

	public String getTeams() {
		String res = "";
		for (int i = 0; i < teams.size(); i++)
			res += teams.get(i) + "\n\t";
		return res;
	} // end getTeams()

	public String getLeagueName() {
		return this.leagueName;
	} // end getLeagueName()

	public void setLeagueName(String newLeagueName) {
		this.leagueName = newLeagueName;
	} // end setLeagueName()

	public void addTeam(Team newTeam) {
		this.teams.add(newTeam);
	} // end addTeam()

	public void removeTeam(int index) {
		this.teams.remove(index);
	} // end removeTeam()

}
