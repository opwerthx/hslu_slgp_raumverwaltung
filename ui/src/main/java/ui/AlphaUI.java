package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import ui.controller.NavigationController;

public class AlphaUI extends Application {
	@Override
	public void start(Stage stage) {

		try {				
										Platform.setImplicitExit(false);
										/* Haupt-Container */
										BorderPane mainRoot = new BorderPane();
								
										mainRoot.setMinWidth(1060);
										mainRoot.setMinHeight(600);

										mainRoot.setPrefWidth(1060);
										mainRoot.setPrefHeight(600);

										mainRoot.setMaxWidth(1060);
										mainRoot.setMaxHeight(600);

										stage.setResizable(false);
										
										Scene scene = new Scene(mainRoot);

										/* NavigationController erstellen */
										NavigationController.init(mainRoot);
										/* HomeView laden */
										NavigationController.getInstance().showHomeView();
															
										stage.setTitle("AlphaRooms - Willkommen");
										stage.setScene(scene);
										stage.show();					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
