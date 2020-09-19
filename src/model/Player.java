//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.io.Serializable;

public class Player extends Person implements Serializable {
	private int numGoals;
	private boolean isGoalie;

	/**
	 * Constructs and initializes Player object
	 * 
	 * @param String
	 *            firstname
	 * @param String
	 *            middlename
	 * @param String
	 *            lastname
	 * @param int
	 *            phone
	 * @param String
	 *            email
	 * @param int
	 *            numgoals
	 * @param boolean
	 *            isgoalie
	 */
	public Player(String f, String m, String l, int p, String e, int ng, boolean ig) {
		super(f, m, l, p, e);
		this.numGoals = ng;
		this.isGoalie = ig;
	} // end constructor Player()

	/**
	 * @return int numGoals
	 */
	public int getNumGoals() {
		return this.numGoals;
	} // end getNumGoals()

	/**
	 * @return boolean isgoalie
	 */
	public boolean getIsGoalie() {
		return this.isGoalie;
	} // end getIsGoalie()

	/**
	 * @param int
	 *            newGoals
	 */
	public void setNumGoals(int newGoals) {
		this.numGoals = newGoals;
	} // end setNumGoals()

	/**
	 * @param boolean
	 *            newGoalie
	 */
	public void setIsGoalie(boolean newGoalie) {
		this.isGoalie = newGoalie;
		this.numGoals = 0;
	} // end setIsGoalie()

	public String toString() {
		if (this.isGoalie == true) {
			return this.getName() + " " + this.getPhone() + " " + this.getEmail() + " " + this.numGoals + " IsGoalie";
		} else {
			return this.getName() + " " + this.getPhone() + " " + this.getEmail() + " " + this.numGoals + " NotGoalie";
		}
	} // end toString()

}
