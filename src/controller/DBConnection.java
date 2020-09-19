//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project
//this is a test commit
package controller;

import java.sql.*;

public class DBConnection {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/OOP_Project";
    static final String USER = "root";
    static final String PASS = "root";

	// ---------------------------------------------------------------------------------
	// -------------------------------- getConnection()
	// ---------------------------------------------------------------------------------
    /**
     * @param String databaseName
     * @return
     */
    public static Connection getConnection(String databaseName) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Successful connection to " + databaseName + " database");
            
            return connection;
            
            
            
        }catch (Exception e) {
            System.out.println("The error was "+ e);
        }
        return null;
    }
}