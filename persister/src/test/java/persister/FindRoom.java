package persister;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Room;

public class FindRoom {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      Room room = entitymanager.find( Room.class, 51 );
		      
		      System.out.println("room ID = " + room.getRoomId());
		      System.out.println("room NAME = " + room.getRoomName());
		      System.out.println("room LOCATION = " + room.getRoomLocation());
		      System.out.println("room ROWFLAG = " + room.getRowFlag());
	   }
}
