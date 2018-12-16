package persister;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Booking;
import persister.Room;
import persister.User;

public class CreateBooking {
	
	 public static void main( String[ ] args ) {
		   
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );

	     Booking booking = new Booking( );
	     
	     Date date= new Date();     
	     long time = date.getTime();
	     Timestamp ts = new Timestamp(time);
	      
	     booking.setBkFrom(ts);
	     booking.setBkTo(ts);
	     booking.setBookingUtil(4);
	     booking.setCTs(ts);
	     booking.setMTs(ts);
	     booking.setCUser("test");
	     booking.setMUser("test");
	     booking.setRowFlag("ADDED");
	     
	     Room room = entitymanager.find( Room.class, 51 );
	     User user = entitymanager.find( User.class, 1 );
	     
	     booking.setRoom(room);
	     booking.setUser(user);

	     entitymanager.persist( booking );
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( ); 
	 }
	
}
