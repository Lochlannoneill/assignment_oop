//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package model;

import java.io.Serializable;

public class Person implements Serializable {
	private Name name;
	private int phone;
	private String email;

	/**
	 * Constructs and initializes Person object
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
	 */
	public Person(String f, String m, String l, int p, String e) {
		this.name = new Name(f, m, l);
		this.phone = p;
		this.email = e;
	} // end constructor Person()

	/**
	 * This method uses the Name class to concatenate a first, middle and last name.
	 * 
	 * @return Name name
	 */
	public Name getName() {
		return this.name;
	} // end getName()

	/**
	 * @return int phone
	 */
	public int getPhone() {
		return this.phone;
	} // end getPhone()

	/**
	 * @return
	 */
	public String getEmail() {
		return this.email;
	} // end getEmail()

	/**
	 * This class uses the Name class to create a Name object for the Person
	 * 
	 * @param Name
	 *            newName
	 */
	public void setName(Name newName) {
		this.name = newName;
	} // end setName()

	/**
	 * @param int
	 *            newPhone
	 */
	public void setPhone(int newPhone) {
		this.phone = newPhone;
	} // end setPhone()

	/**
	 * @param String
	 *            newEmail
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	} // end setEmail()
}
