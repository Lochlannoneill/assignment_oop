//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IntroPane extends Tab {
	
	public IntroPane() {
		Label labelName = new Label("Student name:");
		Label labelId = new Label("Student ID:");
		Label labelAssignment = new Label("Assignment:");
		
		Text textName = new Text("Lochlann O Neill");
		textName.setFont(Font.font ("Verdana"));
		textName.setFill(Color.DARKGREY);
		Text textId = new Text("R00175741");
		textId.setFont(Font.font ("Verdana"));
		textId.setFill(Color.DARKGREY);
		Text textAssignment = new Text("Comp 7013 –OOProj- Project");
		textAssignment.setFont(Font.font ("Verdana"));
		textAssignment.setFill(Color.DARKGREY);
		
		GridPane gridpane = new GridPane();
		gridpane.addRow(0, labelName, textName);
		gridpane.addRow(1,  labelId, textId);
		gridpane.addRow(2,  labelAssignment, textAssignment);
		gridpane.setMinWidth(300);
		gridpane.setVgap(8);
		gridpane.setHgap(32);
		
		CheckBox checkBox0 = new CheckBox("Provide a uml diagram of the classes in this system\t(2 Marks)");
        checkBox0.setSelected(true);
        
        CheckBox checkBox1 = new CheckBox("Gui\t\t\t\t\t\t\t\t\t\t\t(10 Marks)                                              ");
        checkBox1.setSelected(true);
        
        CheckBox checkBox2 = new CheckBox("Classes\t\t\t\t\t\t\t\t\t\t(10 Marks)");
        checkBox2.setSelected(true);
        
        CheckBox checkBox3 = new CheckBox("Functionality\t\t\t\t\t\t\t\t\t(10 Marks)");
        checkBox3.setSelected(true);
        
        CheckBox checkBox4 = new CheckBox("Persist all information to a database\t\t\t\t(40 Marks JPA) (20 for jdbc)");
        checkBox4.setSelected(true);
        
        CheckBox checkBox5 = new CheckBox("Document (Javadoc) and test (junit) some code\t\t(5 Marks)");
        checkBox5.setSelected(true);
        
        CheckBox checkBox6 = new CheckBox("Use an appropriate package structure\t\t\t\t(3 Marks)");
        checkBox6.setSelected(true);
        
        CheckBox checkBox7 = new CheckBox("Use MVC\t\t\t\t\t\t\t\t\t\t(10 Marks)");
        checkBox7.setSelected(true);
        
        CheckBox checkBox8 = new CheckBox("Show memory usage\t\t\t\t\t\t\t(5 Marks)");
        checkBox8.setSelected(true);
        
        CheckBox checkBox9 = new CheckBox("Test your classes(just show some testing)\t\t\t(5 marks)");
        checkBox9.setSelected(true);
        
        CheckBox checkBox10 = new CheckBox("1.\tAdd a new Team, Player or Manager and add Player/Manager to a Team\t\t\t\t(5 marks)");
        checkBox10.setSelected(true);
        
        CheckBox checkBox11 = new CheckBox("2.\tRemove a Player\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(5 marks)");
        checkBox11.setSelected(true);
        
        CheckBox checkBox12 = new CheckBox("3.\tSearch for a Player by supplying the Player name. Display goals and Manager details\t\t(5 marks)");
        checkBox12.setSelected(false);
        
        CheckBox checkBox13 = new CheckBox("4.\tDisplay all players in a particular Team.\t\t\t\t\t\t\t\t\t\t\t(5 marks)");
        checkBox13.setSelected(true);
        
        CheckBox checkBox14 = new CheckBox("5.\tDisplay all Teams in the League\t\t\t\t\t\t\t\t\t\t\t\t(5 marks)");
        checkBox14.setSelected(true);
        
        CheckBox checkBox15 = new CheckBox("6.\tDisplay Managers with both ordering\t\t\t\t\t\t\t\t\t\t\t(5 marks)");
        checkBox15.setSelected(true);
        
        
        
        
//        Add a new Team, Player or Manager and add Player/Manager to a Team. 
//        Remove a Player.
//        Search for a Player by supplying the Player name. Display goals and Manager details.
//               4.    Display all players in a particular Team.
//               5.    Display all Teams in the League.
//               6.    Display Managers with both ordering.

        VBox vboxCheckboxes = new VBox();
        vboxCheckboxes.getChildren().addAll(
        		checkBox0, checkBox1, checkBox2, checkBox3,
        		checkBox4, checkBox5, checkBox6, checkBox7,
        		checkBox8, checkBox9, checkBox10, checkBox11,
        		checkBox12, checkBox13, checkBox14, checkBox15	
        );
        
		setText("Introduction");
		FlowPane flowpaneLayout = new FlowPane(Orientation.VERTICAL, gridpane, vboxCheckboxes);
		flowpaneLayout.setVgap(16);
		setContent(flowpaneLayout);
	}
	
}
