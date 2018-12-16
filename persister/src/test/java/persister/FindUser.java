package persister;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.User;

public class FindUser {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      User user = entitymanager.find( User.class, 1 );
		      
		      System.out.println("User ID = " + user.getUserId());
		      System.out.println("User NAME = " + user.getUsername());
		      System.out.println("User email = " + user.getEmail());
		      System.out.println("User ROWFLAG = " + user.getRowFlag());	
	   }
}
