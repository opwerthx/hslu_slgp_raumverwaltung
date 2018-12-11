package srv;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.List;

import api.Booking;
import api.RMI_Booking;
import persister.ManageBooking;

	
public class BookingImpl extends UnicastRemoteObject implements RMI_Booking  {
	
	private static final long serialVersionUID = 1L;
    private List<Booking> bookingList;
    
    protected BookingImpl(List<Booking> list) throws RemoteException {
        super();
        this.setBookingList(list);
    }	    
    
	private Integer bookingId;
	private Timestamp bkFrom;
	private Timestamp bkTo;
	private Integer bookingUtil;
	private Timestamp cTs;
	private String cUser;
	private Timestamp mTs;
	private String mUser;
	private String rowFlag;
	private Integer user;
	private Integer room;
	
    
    @Override
	public List<Booking> allBookings() throws RemoteException {
		
    	ManageBooking managebooking = new ManageBooking();
        return managebooking.allBookings();
	}
	@Override
	public String createBooking(Booking pBooking) throws RemoteException {
    	ManageBooking managebooking = new ManageBooking();
		return managebooking.createBooking(pBooking);
	}
	@Override
	public String cancelBooking(Integer pID, String pmUser) throws RemoteException {
    	ManageBooking managebooking = new ManageBooking();
        return managebooking.cancelBooking(pID, pmUser);
	}
	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}



}

