package ui.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class NavigationController {

	private static NavigationController INSTANCE = null;
	private static BorderPane mainRoot;
	

	private NavigationController(BorderPane mainRoot) {
		NavigationController.mainRoot = mainRoot;
	}

	public synchronized static NavigationController init(BorderPane mainRoot) {

		INSTANCE = new NavigationController(mainRoot);
		return INSTANCE;

	}

	public static NavigationController getInstance() {

		if (mainRoot == null) {
			throw new AssertionError("Please call the init method first!");
		}

		return INSTANCE;
	}

	public void showHomeView() {

		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("HomeView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void showBookingView(String user) {
		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("BookingView.fxml"));
			mainRoot.setCenter(root);
			Context.getInstance().setLoginUser(user);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showBookingView() {
		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("BookingView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showAddView() {

		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			/* MenuBar im 'top' Bereich des Haupt-Containers ablegen */
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("AddView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void showEditView() {

		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			/* MenuBar im 'top' Bereich des Haupt-Containers ablegen */
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("EditView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void showRoomMngt() {

		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			/* MenuBar im 'top' Bereich des Haupt-Containers ablegen */
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("RoomView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void showUserMngt() {

		try {
			FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
			/* MenuBar im 'top' Bereich des Haupt-Containers ablegen */
			mainRoot.setTop(menuBar);
			AnchorPane root = FXMLLoader.load(getClass().getResource("UserView.fxml"));
			mainRoot.setCenter(root);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void beenden() {
		Platform.exit();
	}

	public void showStatView() {
			try {
				FlowPane menuBar = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
				/* MenuBar im 'top' Bereich des Haupt-Containers ablegen */
				mainRoot.setTop(menuBar);
				AnchorPane root = FXMLLoader.load(getClass().getResource("StatView.fxml"));
				mainRoot.setCenter(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

}
