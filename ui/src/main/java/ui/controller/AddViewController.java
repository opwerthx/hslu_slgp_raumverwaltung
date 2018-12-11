package ui.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import api.Booking;
import api.Room;
import api.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AddViewController implements Initializable  {
	private List<RoomWrapper> roomlist = new ArrayList<>();
	private List<RoomWrapper> roomfilterlist = new ArrayList<>();

    @FXML
    private Button cancel;

    @FXML
    private Button search;

    @FXML
    private ComboBox<Integer> util;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private Button saver;

    @FXML
    private DateTimePicker from;

    @FXML
    private ComboBox<String> booking_for;

    @FXML
    private DateTimePicker to;

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
    void cancel(ActionEvent event) {
		NavigationController.getInstance().showHomeView();
	}
    @FXML    
    void saver(ActionEvent event) {
    	save();
    }
    
	private static final String MESSAGE_HINZUFUEGEN_MISSLUNGEN = "Das Hinzufügen konnte nicht erfolgreich abgeschlossen werden.";
	private static final String MESSAGE_HINZUFUEGEN_ERFOLGREICH = "Das Hinzufügen wurde erfolgreich durchgeführt.";
	private String username;
	private Booking booking;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		System.out.println("init addview");
		
		booking = new Booking(null, null, null, null, null, null, null, null, null, null, null);
		
		from.setEditable(true);
		to.setEditable(true);
		ObservableList<Integer> pListUtil = FXCollections.observableArrayList(1	,2	,3	,4	,5	,6	,7	,8	,9	,10	,11	,12	,13	,14	,15	,16	,17	,18	,19	,20	,21	,22	,23	,24	,25	,26	,27	,28	,29	,30	,31	,32	,33	,34	,35	,36	,37	,38	,39	,40	,41	,42	,43	,44	,45	,46	,47	,48	,49	,50	,51	,52	,53	,54	,55	,56	,57	,58	,59	,60	,61	,62	,63	,64	,65	,66	,67	,68	,69	,70	,71	,72	,73	,74	,75	,76	,77	,78	,79	,80	,81	,82	,83	,84	,85	,86	,87	,88	,89	,90	,91	,92	,93	,94	,95	,96	,97	,98	,99	,100,200);
		util.setItems(pListUtil);
		util.getSelectionModel().selectFirst();
		
		List<Room> roomList = Context.getInstance().getRMI_Room().getRooms();
		for (Room p : roomList) {
			roomlist.add(new RoomWrapper(p));
			//System.out.println(p.getRoomName() + " " +p.getRoomEquipment() + " " +p.getRoomLocation() + " " +p.getRoomId() + " " +p.getRoomMaxUtil() + " " +p.getRoomMinUtil() + " " +p.getRoomEquipment());
		}
			
		
		username = Context.getInstance().getLoginUser();
		User login = Context.getInstance().getRMI_User().getUser(username);
		String role = login.getRole();

		Collection<User> userList = Context.getInstance().getRMI_User().allUser();
		if(role.equals("Admin")) {
			for (User p : userList) {
				booking_for.getItems().add(p.getUsername());
			}
		}
		else
		{
			booking_for.getItems().add(username);
		}
		
		booking_for.getSelectionModel().selectFirst();
		
		/* Tabelle initialisieren */
	    col_id.setCellValueFactory(new PropertyValueFactory<>("roomId"));     
    	col_min.setCellValueFactory(new PropertyValueFactory<>("RoomMinUtil"));      
    	col_max.setCellValueFactory(new PropertyValueFactory<>("RoomMaxUtil"));      	   
    	col_room.setCellValueFactory(new PropertyValueFactory<>("RoomName"));      	   
    	col_equipment.setCellValueFactory(new PropertyValueFactory<>("roomEquipment"));      	   
    	col_location.setCellValueFactory(new PropertyValueFactory<>("roomLocation"));       	
		
		/* Tabelle aktualisieren */
		updateTable();

		/* Binding für die Schaltfläche 'Bearbeiten' erstellen */
		saver.disableProperty().bind(Bindings.size(table_room.getItems()).lessThan(1));

	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}


	private void updateTable() throws RemoteException {
		//if (from.getDateTimeValue().equals(null) || to.getDateTimeValue().equals(null) || util.getValue().equals(null))
		roomlist.clear();
		List<Room> tempListe = Context.getInstance().getRMI_Room().getRooms();
		for (Room p : tempListe) {
			roomlist.add(new RoomWrapper(p));
		}
		table_room.getItems().clear();
		table_room.getItems().addAll(roomlist);
		table_room.getSelectionModel().select(0);
	}

    @FXML
   public void save() {
    	
try {
	System.out.println("save begin");
			
		LocalDateTime tsfrom = from.getDateTimeValue();
		LocalDateTime tsto = to.getDateTimeValue();
		
		lblErrorMessage.setText("");

		Timestamp ts_from = Timestamp.valueOf(tsfrom); 
		Timestamp ts_to = Timestamp.valueOf(tsto);
	
		booking.setBkFrom(ts_from);
		booking.setBkTo(ts_to);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		booking.setBooking_for(booking_for.getValue());
		booking.setRoomId(table_room.getSelectionModel().getSelectedItem().getRoomId());
		booking.setBookingUtil(util.getValue());
		booking.setcTs(now);
		booking.setcUser(username);
		booking.setmUser(username);
		booking.setRowFlag("ADDED");
		booking.setmTs(now);

		/* Speichern */
		System.out.println("save now");
		System.out.print(Context.getInstance().getRMI_Booking().createBooking(booking));

		/* Info-Dialog anzeigen */
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Buchung hinzufügen");
		alert.setHeaderText("Information");
		alert.setContentText(MESSAGE_HINZUFUEGEN_ERFOLGREICH);
		alert.showAndWait();
		NavigationController.getInstance().showBookingView();


		} catch (Exception e) {
			lblErrorMessage.setText(MESSAGE_HINZUFUEGEN_MISSLUNGEN);
		}
	}

	@FXML
	public void suchen() {
		Timestamp ts_from = Timestamp.valueOf(from.getDateTimeValue()); 
		Timestamp ts_to = Timestamp.valueOf(to.getDateTimeValue());
		List<Room> tempListe = null;
		try {
			tempListe = Context.getInstance().getRMI_Room().getOptRooms(ts_from,ts_to,util.getValue());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		roomfilterlist.clear();
		for (Room p : tempListe) {
			roomfilterlist.add(new RoomWrapper(p));
		}
		table_room.getItems().clear();
		table_room.getItems().addAll(roomfilterlist);
		table_room.getSelectionModel().select(0);
		table_room.refresh();
	}
	
	@FXML
	public void filter() {
	
	}
}
