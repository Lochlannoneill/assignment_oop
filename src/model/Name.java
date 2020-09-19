//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.io.Serializable;

public class Name implements Serializable {
	private String firstname;
	private String middlename;
	private String lastname;

	/**
	 * Constructs and initializes Name object
	 * 
	 * @param String
	 *            firstname
	 * @param String
	 *            middlename
	 * @param String
	 *            lastname
	 */
	public Name(String f, String m, String l) {
		this.firstname = f;
		this.middlename = m;
		this.lastname = l;
	} // end constructor Name()

	/**
	 * @return String firstname
	 */
	public String getFirstName() {
		return this.firstname;
	} // getFirstName()

	/**
	 * @return String middlename
	 */
	public String getMiddleName() {
		return this.middlename;
	} // end getMiddleName()

	/**
	 * @return String lastname
	 */
	public String getLastName() {
		return this.lastname;
	} // end getLastName()

	/**
	 * @param String
	 *            newFirst
	 */
	public void setFirstName(String newFirst) {
		this.firstname = newFirst;
	} // end setFirstName()

	/**
	 * @param String
	 *            newMiddle
	 */
	public void setMiddleName(String newMiddle) {
		this.middlename = newMiddle;
	} // end setMiddleName

	/**
	 * @param String
	 *            newLast
	 */
	public void setLastName(String newLast) {
		this.lastname = newLast;
	} // end setLastName

	public String toString() {
		String res = firstname + " ";

		if (!middlename.equals(" ")) {
			res += middlename + " ";
		}
		res += lastname;

		return res;
	} // end toString()
}
