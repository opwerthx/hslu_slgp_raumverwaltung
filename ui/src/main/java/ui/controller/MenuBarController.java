package ui.controller;

import javafx.fxml.FXML;

public class MenuBarController {

	@FXML
	public void showHomeView() {
		NavigationController.getInstance().showHomeView();
	}

	@FXML
	public void datenLaden() {
		NavigationController.getInstance().showBookingView();
	}

	@FXML
	public void showHinzufuegenView() {
		NavigationController.getInstance().showAddView();
	}
	
	@FXML
	public void mngtRoom() {
		NavigationController.getInstance().showRoomMngt();
	}

	@FXML
	public void mngtUser() {
		NavigationController.getInstance().showUserMngt();
	}

	@FXML
	public void showAboutView() {
		NavigationController.getInstance().showAboutView();
	}
	
	@FXML
	public void showStatView() {
		NavigationController.getInstance().showStatView();
	}

	@FXML
	public void beenden() {
		NavigationController.getInstance().beenden();
	}
	
}
