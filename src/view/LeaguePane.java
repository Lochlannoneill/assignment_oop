//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import controller.TeamController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Team;

//To do:
//	Manager does not work for team; not being added to list, cannot remove in test
//	Load team from database and add to observable list
//	Save new team list to database

public class LeaguePane extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 32;

	private Text actionResponse = new Text();

	@SuppressWarnings("unchecked")
	public LeaguePane() {
		// ---------------------------------------------------------------------------------
		// ------------------------- Labels and input
		// ---------------------------------------------------------------------------------
		Label labelTeamName = new Label("Team Name:");
		Label labelTeamColor = new Label("Team Color:");

		TextField textfieldTeamName = new TextField();
		textfieldTeamName.setPromptText("Team Name");
		TextField textfieldTeamColor = new TextField();
		textfieldTeamColor.setPromptText("Team color");

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		ObservableList<Team> observablelist = FXCollections.observableArrayList(TeamController.getTeam());

		// --NOTE--setCellValueFactory() uses the 'get' methods for the given class
		TableColumn<Team, String> columnTeamName = new TableColumn<>("Team Name");
		columnTeamName.setCellValueFactory(new PropertyValueFactory<Team, String>("TeamName"));
		TableColumn<Team, String> columnTeamColor = new TableColumn<>("Team color");
		columnTeamColor.setCellValueFactory(new PropertyValueFactory<Team, String>("Color"));
		TableColumn<Team, Integer> columnPlayercount = new TableColumn<>("Player Count");
		columnPlayercount.setCellValueFactory(new PropertyValueFactory<Team, Integer>("PlayerCount"));

		TableView<Team> table = new TableView<Team>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE); // dynamic max size
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().addAll(columnTeamName, columnTeamColor, columnPlayercount);
		table.setItems(observablelist);

		// ---------------------------------------------------------------------------------
		// -------------------------------- ActivityButtons
		// ---------------------------------------------------------------------------------
		Button buttonAdd = new Button("Add");
		buttonAdd.setMinWidth(BUTTONWIDTH);
		buttonAdd.setMinHeight(BUTTONHEIGHT);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actionResponse.setFill(Color.RED);
				if (textfieldTeamName.getText().isEmpty()) {
					actionResponse.setText("Team name required");
				} else if (textfieldTeamColor.getText().isEmpty()) {
					actionResponse.setText("Team color required");
				} else {
					// add new entity to database using the controller
					TeamController.addTeam(textfieldTeamName.getText(), textfieldTeamColor.getText());

					// update gui table using updated database
					ObservableList<Team> observablelist = FXCollections
							.observableArrayList(TeamController.getTeam());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + observablelist.get(0).getTeamName() + "' added to database");

					// clear text inputs
					textfieldTeamName.clear();
					textfieldTeamColor.clear();
				}
			}
		}); // end buttonAdd.setOnAction()

		Button buttonRemove = new Button("Remove");
		buttonRemove.setMinWidth(BUTTONWIDTH);
		buttonRemove.setMinHeight(BUTTONHEIGHT);
		buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// user mouse click determines the selected item of the table
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();

				// cast selected table item into a 'Manager' object
				Team selectedTeam = (Team) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("Select a row to delete");
				} else {
					// remove entity from database using the controller
					TeamController.removeTeam(selectedTeam.getTeamName()); // use phone as unique primary key

					// update gui table using updated database
					table.getItems().remove(selectedTableItem);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + selectedTeam.getTeamName() + "' removed from database");
				}
			}
		}); // end buttonRemove()

		// ---------------------------------------------------------------------------------
		// ------------------------- Layout
		// ---------------------------------------------------------------------------------
		GridPane gridpaneInput = new GridPane();
		gridpaneInput.addRow(0, labelTeamName, textfieldTeamName);
		gridpaneInput.addRow(1, labelTeamColor, textfieldTeamColor);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtonsActivities = new HBox();
		hboxButtonsActivities.getChildren().addAll(buttonAdd, buttonRemove);
		hboxButtonsActivities.setSpacing(16);
		hboxButtonsActivities.setPadding(new Insets(16, 0, 16, 0));

		setText("League");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtonsActivities, table, actionResponse);
		setContent(vboxLayout);

	}
}
