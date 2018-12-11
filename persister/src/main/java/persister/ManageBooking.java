package persister;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ManageBooking {
	
	public void Booking(){

	}   
	
	public List<api.Booking> allBookings() {
			   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
			   EntityManager em = emfactory.createEntityManager( );
			   em.getTransaction( ).begin( );
			   TypedQuery<Booking> tQuery =
					   em.createQuery("SELECT b FROM Booking b ", Booking.class);
			   List<persister.Booking> p;
			   try {
				   p = tQuery.getResultList();
				} catch (NoResultException e) {
					System.out.println("No Result");	
					return null;
				}
			   
		        List<api.Booking> resultlist = new ArrayList<>();
			   for (Booking px: p )
			   {
				   resultlist.add(new api.Booking(
				    		px.getBookingId(), 
				    		px.getRoom().getRoomId(), 
				    		px.getBookingUtil(), 
				    		px.getRowFlag(), 
				    		px.getUser().getUsername(), 
				    		px.getCUser(),
				    		px.getMUser(), 
				    		px.getBkFrom(), 
				    		px.getBkTo(),
				    		px.getCTs(), 
				    		px.getMTs(),
				    		px.getRoom().getRoomName()
				    		));
			   }
			   System.out.println(resultlist.toString());
			   System.out.print("getAllBookings successfull");
			   
			   return resultlist; 
		   }
		   	
	public List<api.Booking> findBooking(Integer pID) {
	    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	    
	    EntityManager entitymanager = emfactory.createEntityManager( );
	    entitymanager.getTransaction( ).begin( );
	    Booking px = entitymanager.find( Booking.class, pID );
	    
	    List<api.Booking> resultlist = new ArrayList<>();
	    resultlist.add(new api.Booking(
	    		px.getBookingId(), 
	    		px.getRoom().getRoomId(), 
	    		px.getBookingUtil(), 
	    		px.getRowFlag(), 
	    		px.getUser().getUsername(), 
	    		px.getCUser(),
	    		px.getMUser(), 
	    		px.getBkFrom(), 
	    		px.getBkTo(),
	    		px.getCTs(), 
	    		px.getMTs()
	    		));
		System.out.print("findBookings successfull");
		
		return resultlist; 
	}
	
	public String createBooking(api.Booking pBooking) throws RemoteException {
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );
   		 System.out.print(pBooking.toString());
	     Booking booking = new Booking( );
	     
			Date date= new Date();     
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			booking.setBkFrom(pBooking.getBkFrom());
			booking.setBkTo(pBooking.getBkTo());
			booking.setBookingUtil(pBooking.getBookingUtil());
			booking.setCTs(ts);
			booking.setMTs(ts);
			booking.setCUser(pBooking.getcUser());
			booking.setMUser(pBooking.getmUser());
			booking.setRowFlag(pBooking.getRowFlag());
			booking.setRowFlag("Hinzugefügt");
			
			TypedQuery<User> tQuery =
					   entitymanager.createQuery("SELECT p FROM users p WHERE p.username=?1", User.class);
			   tQuery.setParameter(1, pBooking.getBooking_for());
			   User user;
			   user = tQuery.getSingleResult();
	     
	     System.out.print("getRoom start");
	     Room room = entitymanager.find( Room.class, pBooking.getRoomId());
	     
	     booking.setRoom(room);
	     booking.setUser(user);
	     System.out.print("persist start");
	     entitymanager.persist( booking );
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( ); 
		
	    System.out.print("createBooking successfull");
	    return "createBooking successfull";
	}
	
	public String cancelBooking(Integer pID, String pmUser) throws RemoteException {
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	      
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );
	      Booking booking = entitymanager.find( Booking.class, pID );
	      
		  Date date= new Date();     
		  long time = date.getTime();
		  Timestamp ts = new Timestamp(time);
	      
	      booking.setRowFlag("STORNIERT");
	      booking.setMUser(pmUser);
	      booking.setMTs(ts);
	      entitymanager.getTransaction( ).commit( );
	      
	      //after update
	      System.out.println( booking );
	      entitymanager.close();
	      emfactory.close();
		  System.out.print("cancelBooking successfull");
		  return "cancelBooking successfull";
	}
}
