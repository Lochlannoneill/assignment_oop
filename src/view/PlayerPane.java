//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import controller.PlayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import model.Name;
import model.Player;

public class PlayerPane extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 32;
	private Text actionResponse = new Text();

	@SuppressWarnings("unchecked")
	public PlayerPane() {
		// ---------------------------------------------------------------------------------
		// ------------------------- Labels and input
		// ---------------------------------------------------------------------------------
		Label labelFirstname = new Label("First Name:");
		Label labelMiddlename = new Label("Middle Name:");
		Label labelLastname = new Label("Last Name:");
		Label labelPhone = new Label("Phone:");
		Label labelEmail = new Label("Email:");
		Label labelIsGoalie = new Label("Goalie:");
		Label labelGoals = new Label("Goals/Saves:");

		TextField textfieldFirstname = new TextField();
		textfieldFirstname.setPromptText("First Name");
		TextField textfieldMiddlename = new TextField();
		textfieldMiddlename.setPromptText("Middle Name");
		TextField textfieldLastname = new TextField();
		textfieldLastname.setPromptText("Last Name");
		TextField textfieldPhone = new TextField();
		textfieldPhone.setPromptText("Phone Number");
		TextField textfieldEmail = new TextField();
		textfieldEmail.setPromptText("Email");
		TextField textfieldGoals = new TextField();
		textfieldGoals.setPromptText("Goals/Saves");
		ComboBox<String> comboboxIsGoalie = new ComboBox<>();
		comboboxIsGoalie.setPromptText("Is Goalie");
		comboboxIsGoalie.getItems().addAll("True", "False");

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		// data obtained to put into gui table
		ObservableList<Player> observablelist = FXCollections.observableArrayList(PlayerController.getPlayers());

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
		Button buttonAdd = new Button("Add");
		buttonAdd.setMinWidth(BUTTONWIDTH);
		buttonAdd.setMinHeight(BUTTONHEIGHT);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actionResponse.setFill(Color.RED);
				if (textfieldFirstname.getText().isEmpty()) {
					actionResponse.setText("First name required");
				} else if (textfieldMiddlename.getText().isEmpty()) {
					actionResponse.setText("Middle name required");
				} else if (textfieldLastname.getText().isEmpty()) {
					actionResponse.setText("Last name required");
				} else if (textfieldPhone.getText().isEmpty()) {
					actionResponse.setText("Phone number required");
				} else if (textfieldEmail.getText().isEmpty()) {
					actionResponse.setText("Email required");
				} else if (comboboxIsGoalie.getValue() == null) {
					actionResponse.setText("IsGoalie required");
				} else if (textfieldGoals.getText().isEmpty()) {
					actionResponse.setText("Goals/Saves required");
				} else {
					// add new entity to database using the controller
					PlayerController.addPlayer(textfieldFirstname.getText(), textfieldFirstname.getText(),
							textfieldLastname.getText(), Integer.parseInt(textfieldPhone.getText()),
							textfieldEmail.getText(), Integer.parseInt(textfieldGoals.getText()),
							Boolean.parseBoolean(comboboxIsGoalie.getValue())); // integer instead of boolean since
																				// myslq boolean is 0 or 1

					// update gui table using updated database
					ObservableList<Player> observablelist = FXCollections
							.observableArrayList(PlayerController.getPlayers());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + observablelist.get(0).getName() + "' added to database");

					// clear text inputs
					textfieldFirstname.clear();
					textfieldMiddlename.clear();
					textfieldLastname.clear();
					textfieldPhone.clear();
					textfieldEmail.clear();
					comboboxIsGoalie.valueProperty().set(null);
					textfieldGoals.clear();
				}
			}
		}); // end buttonRemove.setOnAction()

		Button buttonRemove = new Button("Remove");
		buttonRemove.setMinWidth(BUTTONWIDTH);
		buttonRemove.setMinHeight(BUTTONHEIGHT);
		buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// user mouse click determines the selected item of the table
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();

				// cast selected table item into a 'Player' object
				Player selectedPlayer = (Player) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("Select a row to delete");
				} else {
					// remove entity from database using the controller
					PlayerController.removePlayer(selectedPlayer.getPhone()); // use phone as unique primary key

					// update gui table using updated database
					table.getItems().remove(selectedTableItem);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + selectedPlayer.getName() + "' removed from database");
				}
			}
		}); // end buttonRemove.setOnAction()

		// ---------------------------------------------------------------------------------
		// ------------------------- Layout
		// ---------------------------------------------------------------------------------
		GridPane gridpaneInput = new GridPane();
		gridpaneInput.addRow(0, labelFirstname, textfieldFirstname);
		gridpaneInput.addRow(1, labelMiddlename, textfieldMiddlename);
		gridpaneInput.addRow(2, labelLastname, textfieldLastname);
		gridpaneInput.addRow(3, labelPhone, textfieldPhone);
		gridpaneInput.addRow(4, labelEmail, textfieldEmail);
		gridpaneInput.addRow(5, labelIsGoalie, comboboxIsGoalie);
		gridpaneInput.addRow(6, labelGoals, textfieldGoals);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtonsActivities = new HBox();
		hboxButtonsActivities.getChildren().addAll(buttonAdd, buttonRemove);
		hboxButtonsActivities.setSpacing(16);
		hboxButtonsActivities.setPadding(new Insets(16, 0, 16, 0));

		setText("Players");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtonsActivities, table, actionResponse);
		setContent(vboxLayout);

	}
}
