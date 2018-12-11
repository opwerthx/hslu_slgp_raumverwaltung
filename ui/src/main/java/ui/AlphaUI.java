package ui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import api.Booking;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
