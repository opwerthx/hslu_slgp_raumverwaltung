package api;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMI_Booking extends Remote {
    String RO_NAME = "BOOKING_RO";
    List<Booking> allBookings() throws RemoteException;
	String cancelBooking(Integer pID, String pmUser) throws RemoteException;
	String createBooking(Booking pBooking) throws RemoteException;
}