package ui.controller;

import java.lang.management.ManagementFactory;
import java.net.URL;

import java.rmi.RemoteException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;


public class HomeViewController implements Initializable {
	  
		@FXML
	    private TextField server;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Label errorMessage;

	    @FXML
	    private Button login;

	    @FXML
	    private TextField userId;
	    

	    @FXML
	    void processLogin(ActionEvent event) {

	    	errorMessage.setTextFill(Color.FIREBRICK);
	    	errorMessage.setText("Sign in progress");
	   
	    		String user = userId.getText();
	    		String pass = password.getText();
	    		System.out.println(server.getText());
	    		boolean auth = false;
	    		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
	    		try {
					auth = Context.getInstance().getRMI_User().checkUserLogin(user, pass, ManagementFactory.getRuntimeMXBean().getName());
				} catch (RemoteException e) {
					errorMessage.setText("Connection failed " + userId.getText());
					e.printStackTrace();
				}
	    		if (auth)
				{
					errorMessage.setText("Willkommen " + userId.getText());
					errorMessage.setTextFill(Color.GREEN);
					Context.getInstance().setLoginUser(user);
					NavigationController.getInstance().showBookingView(user);
				}
	    		else {
	    			errorMessage.setText("Auth Challange failed for User " + userId.getText());	    			
	    		}
	    	
	    }

	public void init() {
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
