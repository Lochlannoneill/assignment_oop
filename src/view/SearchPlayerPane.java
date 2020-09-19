//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Manager;

//To do:
//	If non existing person is entered

public class SearchPlayerPane extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 20;
	
	private Text actionResponse = new Text();
	private ObservableList<Manager> managerDetails = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public SearchPlayerPane() {
		String firstName;
		String middleName;
		String lastName;
		
		// ---------------------------------------------------------------------------------
		// ------------------------- Labels, input and output
		// ---------------------------------------------------------------------------------
		Label labelPlayerFullname = new Label("Player Full Name:");
		
		TextField textfieldPlayerFullname = new TextField();
		textfieldPlayerFullname.setPromptText("Player Full Name");
		
		// ---------------------------------------------------------------------------------
		// ----------------------------------- Details to display
		// ---------------------------------------------------------------------------------
		Label labelPlayerFullName = new Label("Player Name:");
		Label labelPlayerGoals = new Label("Player Goals:");
		Label labelManagerFullName = new Label("Manager Name:");
		Label labelManagerPhone = new Label("Manager Phone:");
		Label labelManagerEmail = new Label("Manager Email:");
		Label labelManagerDOB = new Label("Manager DOB:");
		Label labelManagerRating = new Label("Manager Rating:");
		
		
		Text textPlayerFullName = new Text();
		textPlayerFullName.setFill(Color.DARKGREY);
		//Text textPlayerGoals = new Text(PlayerController.searchPlayer(firstName, middleName, lastName);
		Text textPlayerGoals = new Text();
		textPlayerGoals.setFill(Color.DARKGREY);
		Text textManagerFullName = new Text();
		textManagerFullName.setFill(Color.DARKGREY);
		Text textManagerPhone = new Text();
		textManagerPhone.setFill(Color.DARKGREY);
		Text textManagerEmail = new Text();
		textManagerEmail.setFill(Color.DARKGREY);
		Text textManagerDOB = new Text();
		textManagerDOB.setFill(Color.DARKGREY);
		Text textManagerRating = new Text();
		textManagerRating.setFill(Color.DARKGREY);
		

		// ---------------------------------------------------------------------------------
		// -------------------------------- ActivityButtons
		// ---------------------------------------------------------------------------------
		Button buttonSearch = new Button("Search");
		buttonSearch.setMinWidth(BUTTONWIDTH);
		buttonSearch.setMinHeight(BUTTONHEIGHT);
		buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actionResponse.setFill(Color.RED);
				if (textfieldPlayerFullname.getText().isEmpty()) {
					actionResponse.setText("Enter name of existing player");
				} else {
					textPlayerFullName.setText("Placeholder");
					textPlayerGoals.setText("Placeholder");
					textManagerFullName.setText("Placeholder");
					textManagerPhone.setText("Placeholder");
					textManagerEmail.setText("Placeholder");
					textManagerDOB.setText("Placeholder");
					textManagerRating.setText("Placeholder");
					
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("Displaying goals/scores and manager details of '" + textfieldPlayerFullname.getText() + "'");
					textfieldPlayerFullname.clear();
				}
			}
		});
		
		FlowPane flowpaneInput = new FlowPane(labelPlayerFullname, textfieldPlayerFullname, buttonSearch, actionResponse);
		flowpaneInput.setHgap(8);
		
		GridPane gridpaneOutput = new GridPane();
		gridpaneOutput.addRow(0, labelPlayerFullName, textPlayerFullName);
		gridpaneOutput.addRow(1,  labelPlayerGoals, textPlayerGoals);
		gridpaneOutput.addRow(2,  labelManagerFullName, textManagerFullName);
		gridpaneOutput.addRow(3,  labelManagerPhone, textManagerPhone);
		gridpaneOutput.addRow(4,  labelManagerEmail, textManagerEmail);
		gridpaneOutput.addRow(5,  labelManagerDOB, textManagerDOB);
		gridpaneOutput.addRow(6,  labelManagerRating, textManagerRating);
		gridpaneOutput.setVgap(8);
		gridpaneOutput.setHgap(32);

		setText("Search Player");
		VBox vboxLayout = new VBox(16, flowpaneInput, gridpaneOutput);
		setContent(vboxLayout);

	}
}
