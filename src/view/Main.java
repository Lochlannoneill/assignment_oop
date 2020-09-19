//Author:			Lochlann O Neill
//Student number:	R00175741
//Module:			Object Oriented Programming (Year02 Semester02)
//Assignment:		Assignment02_part02: Comp 7013 –OOProj- Project

//Notes for correcting:
//	Plugin used to make real-time UML diagrams: http://www.objectaid.com/update/current
//	Table columns can be clicked to sort alphabetically/numerically ascending or descending
//	Table rows can be clicked to remove the data from the database
//	Cleaned code so each pane and controller look almost identical to that of their counterparts while still achieving their goals
//  .jar files used:
//		mysql-connector-java-5.1.49-bin.jar
//		musql-connector-java-5.1.49.jar

package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class Main extends Application {
	private final String ASSIGNMENT = "R00175741_Assignment02_part02: Comp 7013 –OOProj- Project";
	private final int SCENEHEIGHT = 625;
	private final int SCENEWIDTH = 800;

	@Override
	public void start(Stage primaryStage) {
		try {
			// ---------------------------------------------------------------------------------
			// -------------------------------- Application Operations
			// ---------------------------------------------------------------------------------
			Button buttonExit = new Button("Exit");
			buttonExit.setMinWidth(64);
			buttonExit.setOnAction(e -> {
				boolean response = ConfirmAlert.display(ASSIGNMENT, "Exit from the application");
				if (response == true) {
					primaryStage.close();
				}
			});
			HBox hboxApplicationOperations = new HBox(buttonExit);
			hboxApplicationOperations.setSpacing(16);
			hboxApplicationOperations.setPadding(new Insets(0, 0, 8, 8));

			// ---------------------------------------------------------------------------------
			// ------------------------------------- Layout
			// ---------------------------------------------------------------------------------
			TabPane tabs = new TabPane();
			tabs.setTabMinWidth(80);
			tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			tabs.getTabs().add(new IntroPane());
			tabs.getTabs().add(new PlayerPane());
			tabs.getTabs().add(new ManagerPane());
			tabs.getTabs().add(new LeaguePane());
			//tabs.getTabs().add(new SearchPlayerPane());
			//tabs.getTabs().add(new SearchTeamPane());

			BorderPane currentPane = new BorderPane();
			Scene scene = new Scene(currentPane, SCENEWIDTH, SCENEHEIGHT);

			currentPane.setBottom(hboxApplicationOperations);
			currentPane.setCenter(tabs);
			currentPane.prefHeightProperty().bind(scene.heightProperty());
			currentPane.prefWidthProperty().bind(scene.widthProperty());

			primaryStage.setTitle(ASSIGNMENT);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
