//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.io.Serializable;

public class Manager extends Person implements Serializable {
	// private final String DOB; //this will not need a set method since it is a
	// constant
	private String DOB;
	private int rating;

	/**
	 * Constructs and initializes Manager object
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
	 * @param String
	 *            dob
	 * @param int
	 *            rating
	 */
	public Manager(String f, String m, String l, int p, String e, String dob, int r) {
		super(f, m, l, p, e);
		this.DOB = dob;
		this.rating = r;
	} // end constructor Manager()

	/**
	 * @return String date of birth
	 */
	public String getDOB() {
		return this.DOB;
	} // end getDOB()

	/**
	 * @param String
	 *            new date of birth
	 */
	public void setDOB(String newDOB) {
		this.DOB = newDOB;
	} // end setDOB

	/**
	 * @return int rating
	 */
	public int getRating() {
		return this.rating;
	} // end getRating()

	/**
	 * @param int
	 *            newRating
	 */
	public void setRating(int newRating) {
		this.rating = newRating;
	} // end setRating()

	public String toString() {
		return this.getName() + " " + this.getPhone() + " " + this.getEmail() + " " + this.DOB + this.rating;
	} // end toString()
}
