package persister;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Config;

public class CreateConfig {
	
	 public static void main( String[ ] args ) {
		   
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );

	     Config config = new Config( );
	     
	     config.setArea("TEST");
	     config.setConfig("LOGINTYPE");
	     config.setSort(10);
	     config.setValue("PLAIN");
	          
	     entitymanager.persist( config );
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( );
	     
	 }
	
}