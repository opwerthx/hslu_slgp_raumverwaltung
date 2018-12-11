package tester;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import persister.User;


public class CreateUser {
	
	 public static void main( String[ ] args ) {
		   
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );

	      Date date= new Date();     
	      long time = date.getTime();
	      Timestamp ts = new Timestamp(time);
	 
	      User user = new User( ); 
	      user.setEmail("test@stud.hslu.ch");
	      user.setUsername("test");
	      user.setPassword("test");
	      
	      user.setFirstname("testname");
	      user.setName("testnachname");
	      
	      user.setCUser("idarnold");
	      user.setMUser("idarnold");
	      user.setRowFlag("test");
	      user.setRole("TestRole");
	      
	      user.setCreateTime(ts);
	      user.setCTs(ts);
	      user.setMTs(ts);
	      
	      entitymanager.persist( user );
	
	      entitymanager.getTransaction( ).commit( );

	      entitymanager.close( );
	      emfactory.close( );
	   }
}
