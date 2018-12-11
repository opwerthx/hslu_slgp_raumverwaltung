package srv;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;

import api.RMI_Booking;
import api.RMI_Room;
import api.RMI_User;

public class AlphaServer {
public AlphaServer() throws RemoteException {

	
} 
   public static void main(String args[]) throws NotBoundException { 
	     		   
		     try { 
		         Registry registry = LocateRegistry.createRegistry(1099);
		         
		         // Instantiating the implementation class 
		    	 UserImpl UserRO = new UserImpl(); 
		         // Exporting the object of implementation class  
		         RMI_User user_stub = (RMI_User) UnicastRemoteObject.exportObject(UserRO, 0);  
		         // Binding the remote object (stub) in the registry 
		         registry.bind(RMI_User.RO_NAME, user_stub);  
		         // Bind additionals
		    	 BookingImpl BookingRO = new BookingImpl(null);  
		    	 Naming.rebind(RMI_Booking.RO_NAME, BookingRO); 
		     	 RoomImpl RoomRO = new RoomImpl(null);
		    	 Naming.rebind(RMI_Room.RO_NAME, RoomRO);
		    	 
		         System.err.println("Server ready");
		     	}
		     catch(Exception e){
			    	//problem with the client not connected.
			    	//Better to remove it
			    }
		     
	   }
}
