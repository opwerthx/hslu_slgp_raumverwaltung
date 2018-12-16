package persister;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Room;


public class CreateRoom {
	
	 public static void main( String[ ] args ) {
		   
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );

	     Room room = new Room( );

	     room.setRoomLocation("S12");
	     room.setRoomName("Niklaus Wirth");
	     room.setRoomMaxUtil(30);
	     room.setRoomMinUtil(1);
	     room.setRowFlag("ADDED");
	          
	     entitymanager.persist( room );
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( );
	     
	 }
	
}
