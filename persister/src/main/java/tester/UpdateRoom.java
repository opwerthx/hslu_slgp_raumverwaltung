package tester;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Room;

public class UpdateRoom {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      Room room = entitymanager.find( Room.class, 51 );
		      
			  Date date= new Date();     
			  long time = date.getTime();
			  Timestamp ts = new Timestamp(time);
		      
		      //before update
		      System.out.println( room );
		      room.setRoomMaxUtil(3);
		      entitymanager.getTransaction( ).commit( );
		      
		      //after update
		      System.out.println( room );
		      entitymanager.close();
		      emfactory.close();
	   }
}
