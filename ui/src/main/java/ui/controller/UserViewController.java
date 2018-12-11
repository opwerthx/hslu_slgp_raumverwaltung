package ui.controller;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.List;


import api.User;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserViewController {

    @FXML
    private Label lblMessage;

    @FXML
    private TableColumn<UserWrapper, Integer> col_id;

    @FXML
    private Label lblMessage3;

    @FXML
    private Label lblMessage1;

    @FXML
    private Label lblMessage2;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtmail;

    @FXML
    private TableColumn<UserWrapper, String> col_name;

    @FXML
    private ComboBox<String> cmbrole;

    @FXML
    private TextField txtpassword;

    @FXML
    private TableColumn<UserWrapper, String> col_username;

    @FXML
    private TableColumn<UserWrapper, String> col_firstname;

    @FXML
    private TextField txtname;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<UserWrapper, String> col_role;

    @FXML
    private TableColumn<UserWrapper, String> col_mail;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<UserWrapper, String> col_password;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtusername;

	private List<UserWrapper> userlist = new ArrayList<>();
	
    @FXML
    private TableView<UserWrapper> table_user;
    
	private UserWrapper selectedUser;

		@FXML
		private void onAdd() {

			// if selectedUser not null, leave the method (invoke the update method)
			if (selectedUser != null) {
				return;
			}

			lblMessage.setText("");

			// read the values¨
			String user = txtusername.getText();
			String mail = txtmail.getText();
			String name = txtname.getText();
			String password = txtpassword.getText();
			String role = cmbrole.getValue().toString();

			// check if entered values are valid
			if (user.trim().length() > 0 && password.trim().length() > 0 && name != null) {

				try {
					Context.getInstance().getRMI_User().createUser(user,mail,name,password,role);				
					// show the message
					lblMessage.setText("User added");
					updateTable();		
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				// reset the view
				onReset();
			}
		}

		@FXML
		private void onRemove() {

			lblMessage.setText("");

			// geth the selected item
			UserWrapper selectedUser = table_user.getSelectionModel().getSelectedItem();

			// if not null, remove
			if (selectedUser != null) {
				try {
					Context.getInstance().getRMI_User().deleteUser(selectedUser.getUserId());
					table_user.getItems().remove(selectedUser);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// show an appropriate message
			lblMessage.setText("User removed.");
			}
		}

		@FXML
		private void onReset() {
			// reset all components and clear all selections
			txtusername.setText("");
			txtmail.setText("");
			txtname.setText("");
			txtpassword.setText("");			
			cmbrole.getSelectionModel().clearSelection();
			cmbrole.getSelectionModel().clearAndSelect(0);
			table_user.getSelectionModel().clearSelection();
			// reset the selectedUser
			selectedUser = null;
		}

		@FXML
		private void onUpdate() {

			// if no person selected, leave the method
			if (selectedUser == null) {
				return;
			}

			// get the id of the selected person
			int id = selectedUser.getUserId();

			// try to find the user with 'id' (selected user)
			for (UserWrapper tempUserWrapper : table_user.getItems()) {
				if (tempUserWrapper.getUserId() == id) {
					/* keep the position of the object in the observable list */
					int index = table_user.getItems().indexOf(tempUserWrapper);
					// exit the loop
					break;
				}
			}
			
			// read the values
			String user = txtusername.getText();
			String mail = txtmail.getText();
			String name = txtname.getText();
			String password = txtpassword.getText();
			String role = cmbrole.getValue().toString();
						
			// check if entered values are valid
			if (user.trim().length() > 0 && name.trim().length() > 0 && password != null) {

				//get the user			
				User r = new User();
				try {
					r = Context.getInstance().getRMI_User().getUser(selectedUser.getUserName());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				r.setUsername(user);
				r.setEmail(mail);
				r.setName(name);
				r.setPassword(password);
				r.setRole(role);
					
				//update the user 
				try {
					Context.getInstance().getRMI_User().updateUser(r);
					// remove the selectedUser object form the observable list (model)
					table_user.getItems().remove(selectedUser);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// reset the view
				onReset();
				try {
					updateTable();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
					
		}

		@FXML
		public void initialize() {
			cmbrole.getItems().add("User");
			cmbrole.getItems().add("Admin");
			cmbrole.getSelectionModel().selectFirst();

			List<User> userList = null;
			try {
				userList = Context.getInstance().getRMI_User().allUser();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			for (User p : userList) {
				userlist.add(new UserWrapper(p));
			}
			
			 System.out.print(ManagementFactory.getRuntimeMXBean().getName());
			/* Tabelle initialisieren */
		    col_id.setCellValueFactory(new PropertyValueFactory<>("userId"));     
		    col_username.setCellValueFactory(new PropertyValueFactory<>("userName"));      
		    col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));      	   
		    col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));      	   
		    col_name.setCellValueFactory(new PropertyValueFactory<>("name"));      	   
		    col_password.setCellValueFactory(new PropertyValueFactory<>("password"));       	
		    col_role.setCellValueFactory(new PropertyValueFactory<>("role"));      
		    
			/* Binding für die Schaltfläche 'Bearbeiten' erstellen */
			btnUpdate.disableProperty().bind(Bindings.size(table_user.getItems()).lessThan(1));
			/* get the selected item (e.g. for edit) */
			table_user.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserWrapper>() {

				@Override
				public void changed(ObservableValue<? extends UserWrapper> observable, UserWrapper oldValue, UserWrapper newValue) {

					if (table_user.getSelectionModel().getSelectedItem() != null) {

						selectedUser = table_user.getSelectionModel().getSelectedItem();
						txtmail.setText(selectedUser.getEmail());
						txtusername.setText(selectedUser.getUserName());
						txtname.setText(selectedUser.getName());
						txtpassword.setText(selectedUser.getPassword());
						cmbrole.getSelectionModel().select(selectedUser.getRole().indexOf(selectedUser.getRole()));
					}

				}
			});

			/* Disable btnRemove if no table item selected */
			btnRemove.disableProperty().bind(table_user.getSelectionModel().selectedItemProperty().isNull());

			/* clear selection if one of textfields or btnAdd has focus */
			txtmail.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());
			txtusername.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());
			txtname.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());
			txtpassword.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());
			cmbrole.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());
			btnAdd.focusedProperty().addListener((e) -> table_user.getSelectionModel().clearSelection());

			// add all created person instances to the model of the tableview
			if (!userlist.isEmpty()) {
				table_user.getItems().addAll(userlist);
			}
		}

		private void updateTable() throws RemoteException {
			userlist.clear();
			List<User> tempListe = Context.getInstance().getRMI_User().allUser();
			for (User p : tempListe) {
				userlist.add(new UserWrapper(p));
			}
			table_user.getItems().clear();
			table_user.getItems().addAll(userlist);
			table_user.getSelectionModel().select(0);
		}
	}
