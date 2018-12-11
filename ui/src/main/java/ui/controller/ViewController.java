package ui.controller;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import api.Booking;
import api.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ui.controller.BookingWrapper;

public class ViewController implements Initializable {

	private List<BookingWrapper> bookinglist = new ArrayList<>();

	   @FXML
	    private TableView<BookingWrapper> table;

	    @FXML
	    private TableColumn<BookingWrapper, Integer> col_bookingUtil;

	    @FXML
	    private TableColumn<BookingWrapper, Timestamp> col_bkfrom;

	    @FXML
	    private TableColumn<BookingWrapper, String> col_rowflag;

	    @FXML
	    private TableColumn<BookingWrapper, Integer> col_id;

	    @FXML
	    private Label navigationTitle;

	    @FXML
	    private TableColumn<BookingWrapper, Integer> col_room;

	    @FXML
	    private TableColumn<RoomWrapper, String> col_roomname;	    
	    
	    @FXML
	    private TableColumn<BookingWrapper, String> col_bookingfor;

	    @FXML
	    private TableColumn<BookingWrapper, Timestamp> col_bkto;


	@FXML
	private Button btnBearbeiten;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			System.out.println(Context.getInstance().getLoginUser());
			List<Booking> tempListe = Context.getInstance().getRMI_Booking().allBookings();
			for (Booking p : tempListe) {
				bookinglist.add(new BookingWrapper(p));
			}
			 System.out.print(ManagementFactory.getRuntimeMXBean().getName());
			/* Tabelle initialisieren */
	    	col_id.setCellValueFactory(new PropertyValueFactory<>("bookingId"));      
	    	col_rowflag.setCellValueFactory(new PropertyValueFactory<>("rowFlag"));      	   
	    	col_bookingfor.setCellValueFactory(new PropertyValueFactory<>("booking_for"));      	   
	    	col_bookingUtil.setCellValueFactory(new PropertyValueFactory<>("bookingUtil"));      	   
	    	col_room.setCellValueFactory(new PropertyValueFactory<>("roomId"));   
	    	col_roomname.setCellValueFactory(new PropertyValueFactory<>("roomName"));      
	    	col_bkfrom.setCellValueFactory(new PropertyValueFactory<>("bkFrom"));   
	    	col_bkto.setCellValueFactory(new PropertyValueFactory<>("bkTo"));   

			/* Auf DoubleClick reagieren */
	    	table.setRowFactory(new Callback<TableView<BookingWrapper>, TableRow<BookingWrapper>>() {
				@Override
				public TableRow<BookingWrapper> call(TableView<BookingWrapper> param) {
					TableRow<BookingWrapper> tRow = new TableRow<>();

					tRow.setOnMouseClicked(event -> {
						if (event.getClickCount() == 2 && !tRow.isEmpty()) {
							BookingWrapper item = tRow.getItem();

							Booking booking = item.getBooking();
							try {
								
								String username = Context.getInstance().getLoginUser();
								User login = Context.getInstance().getRMI_User().getUser(username);
								String role = login.getRole();
								
							if (booking != null) {
								Context.getObjectMap().put("selectedPerson", item.getBooking());
								
								Alert alert = new Alert(AlertType.CONFIRMATION);
			                      alert.setTitle("Stornierung einer Reservierung");
			                      alert.setHeaderText("Wollen Sie die gewählte Reservierung stornieren?");
			                      alert.setContentText("Bist Du bereit die Verantwortung zu tragen?");

			                      Optional<ButtonType> result = alert.showAndWait();
			                      if (result.get() == ButtonType.OK){
			                    	  try {
			                    		if(booking.getcUser().equals(login.getUsername()) || role.equals("Admin")) {
			                    			Context.getInstance().getRMI_Booking().cancelBooking(booking.getBookingId(), username );
			                    		}
			                    		else {
			                    			Alert warning = new Alert(AlertType.WARNING);
						                    warning.setTitle("Stornierung einer Reservierung");
						                    warning.setHeaderText("Sie könnnen nur Ihre eigenen Buchungen stornieren.");
						                    warning.setContentText("Sie können die Verantwortung also nicht tragen!");
						                    warning.showAndWait();
			                    		}
										updateTable();
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
			                      }
			                      
							}
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});

					return tRow;
				}
			});

			/* Tabelle aktualisieren */
			updateTable();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void updateTable() throws RemoteException {
		table.getItems().clear();
		bookinglist.clear();
		List<Booking> tempListe = Context.getInstance().getRMI_Booking().allBookings();
		for (Booking p : tempListe) {
			bookinglist.add(new BookingWrapper(p));
		}
		table.getItems().addAll(bookinglist);
		table.getSelectionModel().select(0);
	}

	@FXML
	public void bearbeiten() {

		BookingWrapper pWrapper = table.getSelectionModel().getSelectedItem();
		if (pWrapper != null) {
			Context.getObjectMap().put("selectedPerson", pWrapper.getBooking());
			NavigationController.getInstance().showEditView();
		}
	}

	@FXML
	public void abbrechen() {
		NavigationController.getInstance().showHomeView();
	}
}
