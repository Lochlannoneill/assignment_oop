//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

package view;

import controller.ManagerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import model.Manager;
import model.Name;

public class ManagerPane extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 32;
	private Text actionResponse = new Text();

	@SuppressWarnings("unchecked")
	public ManagerPane() {
		// ---------------------------------------------------------------------------------
		// ------------------------- Labels and input
		// ---------------------------------------------------------------------------------
		Label labelFirstname = new Label("First Name:");
		Label labelMiddlename = new Label("Middle Name:");
		Label labelLastname = new Label("Last Name:");
		Label labelPhone = new Label("Phone:");
		Label labelEmail = new Label("Email:");
		Label labelDOB = new Label("Date Of Birth:");
		Label labelRating = new Label("Rating:");

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
		DatePicker datepickerDOB = new DatePicker();
		datepickerDOB.setPromptText("Date");
		ComboBox<String> comboboxRating = new ComboBox<>();
		comboboxRating.setPromptText("Rating");
		comboboxRating.getItems().addAll("1", "2", "3", "4", "5");

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		// data obtained to put into gui table
		ObservableList<Manager> observablelist = FXCollections.observableArrayList(ManagerController.getManagers());

		// --NOTE--setCellValueFactory() uses the 'set' methods for the given class
		TableColumn<Manager, Name> columnFirstName = new TableColumn<>("Name");
		columnFirstName.setCellValueFactory(new PropertyValueFactory<Manager, Name>("Name"));
		TableColumn<Manager, Integer> columnPhone = new TableColumn<>("Phone");
		columnPhone.setCellValueFactory(new PropertyValueFactory<Manager, Integer>("Phone"));
		TableColumn<Manager, String> columnEmail = new TableColumn<>("Email");
		columnEmail.setCellValueFactory(new PropertyValueFactory<Manager, String>("Email"));
		TableColumn<Manager, String> columnDOB = new TableColumn<>("Date Of Birth");
		columnDOB.setCellValueFactory(new PropertyValueFactory<Manager, String>("DOB"));
		TableColumn<Manager, Integer> columnRating = new TableColumn<>("Rating");
		columnRating.setCellValueFactory(new PropertyValueFactory<Manager, Integer>("Rating"));

		TableView<Manager> table = new TableView<Manager>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE); // dynamic max size
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // sum of column widths equal table width
		table.getColumns().addAll(columnFirstName, columnPhone, columnEmail, columnDOB, columnRating);
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
				} else if (datepickerDOB.getValue() == null) {
					actionResponse.setText("Date required");
				} else if (comboboxRating.getValue() == null) {
					actionResponse.setText("Rating required");
				} else {
					// add new entity to database using the controller
					ManagerController.addManager(textfieldFirstname.getText(), textfieldFirstname.getText(),
							textfieldLastname.getText(), Integer.parseInt(textfieldPhone.getText()),
							textfieldEmail.getText(), datepickerDOB.getValue().toString(),
							Integer.parseInt(comboboxRating.getValue())); // integer instead of boolean since myslq
																			// boolean is 0 or 1

					// update gui table using updated database
					ObservableList<Manager> observablelist = FXCollections
							.observableArrayList(ManagerController.getManagers());
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
					datepickerDOB.getEditor().clear();
					comboboxRating.valueProperty().set(null);
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
				Manager selectedManager = (Manager) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("Select a row to delete");
				} else {
					// remove entity from database using the controller
					ManagerController.removeManager(selectedManager.getPhone()); // use phone as unique primary key

					// update gui table using updated database
					table.getItems().remove(selectedTableItem);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + selectedManager.getName() + "' removed from database");
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
		gridpaneInput.addRow(5, labelDOB, datepickerDOB);
		gridpaneInput.addRow(6, labelRating, comboboxRating);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtonsActivities = new HBox();
		hboxButtonsActivities.getChildren().addAll(buttonAdd, buttonRemove);
		hboxButtonsActivities.setSpacing(16);
		hboxButtonsActivities.setPadding(new Insets(16, 0, 16, 0));

		setText("Managers");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtonsActivities, table, actionResponse);
		setContent(vboxLayout);

	}
}
