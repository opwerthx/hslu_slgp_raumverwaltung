package tester;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Config;

public class UpdateConfig {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      Config config = entitymanager.find( Config.class, 101 );
		      
			  Date date= new Date();     
			  long time = date.getTime();
			  Timestamp ts = new Timestamp(time);
		      
		      //before update
		      System.out.println( config );
		      
		      entitymanager.getTransaction( ).commit( );
		      
		      //after update
		      System.out.println( config );
		      entitymanager.close();
		      emfactory.close();
	   }
}
