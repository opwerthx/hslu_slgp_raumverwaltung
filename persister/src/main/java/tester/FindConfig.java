package tester;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.Config;

public class FindConfig {
	   public static void main( String[ ] args ) {
		      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		      
		      EntityManager entitymanager = emfactory.createEntityManager( );
		      entitymanager.getTransaction( ).begin( );
		      Config config = entitymanager.find( Config.class, 101 );
		      
		      System.out.println("config Area = " + config.getArea());
		      System.out.println("config Config = " + config.getConfig());
		      System.out.println("config Value = " + config.getValue());
		      System.out.println("config Sort = " + config.getSort());
	   }
}
