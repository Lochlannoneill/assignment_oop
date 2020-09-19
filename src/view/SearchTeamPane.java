//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import controller.PlayerController;
import controller.TeamController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Manager;
import model.Name;
import model.Player;
import model.Team;

//To do:
//If non existing person is entered

public class SearchTeamPane extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 20;

	private Text actionResponse = new Text();
	private ObservableList<Manager> managerDetails = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	public SearchTeamPane() {
		// ---------------------------------------------------------------------------------
		// ------------------------- Labels, input and output
		// ---------------------------------------------------------------------------------
		Label labelTeamName = new Label("Team Name:");
		TextField textfieldTeamName = new TextField();
		textfieldTeamName.setPromptText("Team Name");

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Manager Details
		// ---------------------------------------------------------------------------------

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		// data obtained to put into gui table
		ObservableList<Player> observablelist = FXCollections.observableArrayList(TeamController.searchTeam(textfieldTeamName.getText()));

		// --NOTE--setCellValueFactory() uses the 'set' methods for the given class
		TableColumn<Player, Name> columnFirstName = new TableColumn<>("Name");
		columnFirstName.setCellValueFactory(new PropertyValueFactory<Player, Name>("Name"));
		TableColumn<Player, Integer> columnPhone = new TableColumn<>("Phone");
		columnPhone.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Phone"));
		TableColumn<Player, String> columnEmail = new TableColumn<>("Email");
		columnEmail.setCellValueFactory(new PropertyValueFactory<Player, String>("Email"));
		TableColumn<Player, Boolean> columnIsGoalie = new TableColumn<>("Is Goalie");
		columnIsGoalie.setCellValueFactory(new PropertyValueFactory<Player, Boolean>("IsGoalie"));
		TableColumn<Player, Integer> columnGoals = new TableColumn<>("Goals/Saves");
		columnGoals.setCellValueFactory(new PropertyValueFactory<Player, Integer>("NumGoals"));

		TableView<Player> table = new TableView<Player>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE); // dynamic max width
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // sum of column widths equal table width
		table.getColumns().addAll(columnFirstName, columnPhone, columnEmail, columnGoals, columnIsGoalie);
		table.setItems(observablelist);

		// ---------------------------------------------------------------------------------
		// -------------------------------- ActivityButtons
		// ---------------------------------------------------------------------------------
		Button buttonSearch = new Button("Search");
		buttonSearch.setMinWidth(BUTTONWIDTH);
		buttonSearch.setMinHeight(20);
		buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actionResponse.setFill(Color.RED);
				if (textfieldTeamName.getText().isEmpty()) {
					actionResponse.setText("Enter name of existing player");
				} else {
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("Displaying players in '" + textfieldTeamName.getText() + "'");
					textfieldTeamName.clear();
				}
					TeamController.searchTeam(textfieldTeamName.getText());
					
					// update gui table using updated database
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("Displaying players in team '" + textfieldTeamName.getText() + "'");
			}
		});

		FlowPane flowpaneInput = new FlowPane(labelTeamName, textfieldTeamName, buttonSearch, actionResponse);
		flowpaneInput.setHgap(8);

		setText("Search Team");
		VBox vboxLayout = new VBox(16, flowpaneInput, table);
		setContent(vboxLayout);

	}
}
