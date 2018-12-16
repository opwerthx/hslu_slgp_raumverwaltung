package persister;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Booking;

public class UpdateBooking {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      Booking booking = entitymanager.find( Booking.class, 151 );
		      
			  Date date= new Date();     
			  long time = date.getTime();
			  Timestamp ts = new Timestamp(time);
		      
		      //before update
		      System.out.println( booking );
		      booking.setRowFlag("EDITED");
		      booking.setMUser("idsandro");
		      booking.setMTs(ts);
		      entitymanager.getTransaction( ).commit( );
		      
		      //after update
		      System.out.println( booking );
		      entitymanager.close();
		      emfactory.close();
	   }
}
