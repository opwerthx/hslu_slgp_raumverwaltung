package ui.controller;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.util.ArrayList;

import java.util.List;

import api.Room;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RoomViewController {

	private List<RoomWrapper> roomlist = new ArrayList<>();
	   @FXML
	    private Label lblMessage;

	    @FXML
	    private Label lblMessage1;

	    @FXML
	    private Label lblMessage2;	
	
    @FXML
    private TableView<RoomWrapper> table_room;
    
    @FXML
    private TableColumn<RoomWrapper, Integer> col_id;

    @FXML
    private TableColumn<RoomWrapper, Integer> col_min;

    @FXML
    private TableColumn<RoomWrapper, Integer> col_max;

    @FXML
    private TableColumn<RoomWrapper, String> col_room;

    @FXML
    private TableColumn<RoomWrapper, String> col_equipment;

    @FXML
    private TableColumn<RoomWrapper, String> col_location;
  
    @FXML
    private ComboBox<Integer> cmbmax;

    @FXML
    private ComboBox<Integer> cmbmin;
    
    @FXML
    private TextField txtequipment;

    @FXML
    private TextField txtroom;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtlocation;
    
//    @FXML
//    void onRemove(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onReset(ActionEvent event) {
//
//    }
//
//    @FXML
//    void onUpdate(ActionEvent event) {
//
//    }

//    @FXML
//    void onAdd(ActionEvent event) {
//
//    }


	private RoomWrapper selectedRoom;

		@FXML
		private void onAdd() {

			// if selectedRoom not null, leave the method (invoke the update method)
			if (selectedRoom != null) {
				return;
			}

			lblMessage.setText("");

			// read the values
			String room = txtroom.getText();
			String location = txtlocation.getText();
			String equipment = txtequipment.getText();
						
			// check if entered values are valid
			if (room.trim().length() > 0 && location.trim().length() > 0 && equipment != null) {

				// create a new room
				Room r = new Room();
				r.setRoomName(room);
				r.setRoomLocation(location);
				r.setRoomEquipment(equipment);
				r.setRoomMinUtil(cmbmin.getValue());
				r.setRoomMaxUtil(cmbmax.getValue());
				
				try {
					Context.getInstance().getRMI_Room().createRoom(r);				
					// show the message
					lblMessage.setText("Room added");
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
			RoomWrapper selectedRoom = table_room.getSelectionModel().getSelectedItem();

			// if not null, remove
			if (selectedRoom != null) {
				try {
					Context.getInstance().getRMI_Room().deleteRoom(selectedRoom.getRoomId());
					table_room.getItems().remove(selectedRoom);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// show an appropriate message
			lblMessage.setText("Room removed.");
			}
		}

		@FXML
		private void onReset() {
			// reset all components and clear all selections
			txtroom.setText("");
			txtlocation.setText("");
			txtequipment.setText("");
			cmbmin.getSelectionModel().clearSelection();
			cmbmin.getSelectionModel().clearAndSelect(0);
			cmbmax.getSelectionModel().clearSelection();
			cmbmax.getSelectionModel().clearAndSelect(0);
			table_room.getSelectionModel().clearSelection();
			// reset the selectedRoom
			selectedRoom = null;
		}

		@FXML
		private void onUpdate() {

			// if no person selected, leave the method
			if (selectedRoom == null) {
				return;
			}

			// get the id of the selected person
			int id = selectedRoom.getRoomId();
			int index;
			// try to find the room with 'id' (selected room)
			for (RoomWrapper tempRoomWrapper : table_room.getItems()) {
				if (tempRoomWrapper.getRoomId() == id) {
					/* keep the position of the object in the observable list */
					index = table_room.getItems().indexOf(tempRoomWrapper);
					// exit the loop
					break;
				}
			}
			
			// read the values
			String room = txtroom.getText();
			String location = txtlocation.getText();
			String equipment = txtequipment.getText();
						
			// check if entered values are valid
			if (room.trim().length() > 0 && location.trim().length() > 0 && equipment != null) {

				//get the room			
				Room r = new Room();
				List<Room> roomList = null;
				try {
					roomList = Context.getInstance().getRMI_Room().getRoom(selectedRoom.getRoomId());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (Room p : roomList) {
					r.setRoomId(p.getRoomId());
					r.setRoomName(p.getRoomName());
					r.setRoomEquipment(p.getRoomEquipment());
					r.setRoomMinUtil(p.getRoomMinUtil());
					r.setRoomMaxUtil(p.getRoomMaxUtil());
					r.setRoomLocation(p.getRoomLocation());
					//System.out.println(p.getRoomName() + " " +p.getRoomEquipment() + " " +p.getRoomLocation() + " " +p.getRoomId() + " " +p.getRoomMaxUtil() + " " +p.getRoomMinUtil() + " " +p.getRoomEquipment());
				}
					
				r.setRoomName(room);
				r.setRoomLocation(location);
				r.setRoomEquipment(equipment);
				r.setRoomMinUtil(cmbmin.getValue());
				r.setRoomMaxUtil(cmbmax.getValue());
					
				
				//update the room 
				try {
					Context.getInstance().getRMI_Room().updateRoom(r);
					// remove the selectedRoom object form the observable list (model)
					table_room.getItems().remove(selectedRoom);
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
			ObservableList<Integer> pListUtil = FXCollections.observableArrayList(1,2,5,7,10,15,20,30,60,70,100,200);
			cmbmin.setItems(pListUtil);
			cmbmin.getSelectionModel().selectFirst();
			cmbmax.setItems(pListUtil);
			cmbmax.getSelectionModel().selectFirst();
			List<Room> roomList = null;
			try {
				roomList = Context.getInstance().getRMI_Room().getRooms();
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			for (Room p : roomList) {
				roomlist.add(new RoomWrapper(p));
				System.out.println(p.getRoomName() + " " +p.getRoomEquipment() + " " +p.getRoomLocation() + " " +p.getRoomId() + " " +p.getRoomMaxUtil() + " " +p.getRoomMinUtil() + " " +p.getRoomEquipment());
			}
				
			 System.out.print(ManagementFactory.getRuntimeMXBean().getName());
			/* Tabelle initialisieren */
		    col_id.setCellValueFactory(new PropertyValueFactory<>("roomId"));     
	    	col_min.setCellValueFactory(new PropertyValueFactory<>("RoomMinUtil"));      
	    	col_max.setCellValueFactory(new PropertyValueFactory<>("RoomMaxUtil"));      	   
	    	col_room.setCellValueFactory(new PropertyValueFactory<>("RoomName"));      	   
	    	col_equipment.setCellValueFactory(new PropertyValueFactory<>("roomEquipment"));      	   
	    	col_location.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));       	
			
			/* Binding für die Schaltfläche 'Bearbeiten' erstellen */
			btnUpdate.disableProperty().bind(Bindings.size(table_room.getItems()).lessThan(1));
			/* get the selected item (e.g. for edit) */
			table_room.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomWrapper>() {

				@Override
				public void changed(ObservableValue<? extends RoomWrapper> observable, RoomWrapper oldValue, RoomWrapper newValue) {

					if (table_room.getSelectionModel().getSelectedItem() != null) {

						selectedRoom = table_room.getSelectionModel().getSelectedItem();
						txtlocation.setText(selectedRoom.getRoomLocation());
						txtroom.setText(selectedRoom.getRoomName());
						txtequipment.setText(selectedRoom.getRoomEquipment());
						cmbmin.getSelectionModel().select(selectedRoom.getRoomMinUtil());
						cmbmax.getSelectionModel().select(selectedRoom.getRoomMaxUtil());
					}

				}
			});

			/* Disable btnRemove if no table item selected */
			btnRemove.disableProperty().bind(table_room.getSelectionModel().selectedItemProperty().isNull());

			/* clear selection if one of textfields or btnAdd has focus */
			txtlocation.focusedProperty().addListener((e) -> table_room.getSelectionModel().clearSelection());
			txtroom.focusedProperty().addListener((e) -> table_room.getSelectionModel().clearSelection());
			cmbmin.focusedProperty().addListener((e) -> table_room.getSelectionModel().clearSelection());
			cmbmax.focusedProperty().addListener((e) -> table_room.getSelectionModel().clearSelection());
			btnAdd.focusedProperty().addListener((e) -> table_room.getSelectionModel().clearSelection());

			// add all created person instances to the model of the tableview
			if (!roomlist.isEmpty()) {
				table_room.getItems().addAll(roomlist);
			}
		}

		private void updateTable() throws RemoteException {
			roomlist.clear();
			List<Room> tempListe = Context.getInstance().getRMI_Room().getRooms();
			for (Room p : tempListe) {
				roomlist.add(new RoomWrapper(p));
			}
			table_room.getItems().clear();
			table_room.getItems().addAll(roomlist);
			table_room.getSelectionModel().select(0);
		}
	}
