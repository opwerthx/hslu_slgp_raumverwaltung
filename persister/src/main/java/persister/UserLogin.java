package persister;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserLogin implements Serializable {


	private static final long serialVersionUID = 2087551531556192267L;

		public UserLogin(){
 		      
	   }
	   
		   public static void main( String[ ] args ) {
			      
		   } 
	    
	   public boolean checkUserLogin(String pUser, String pPass, String pSession) {
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		   
		   EntityManager em = emfactory.createEntityManager( );
		   em.getTransaction( ).begin( );
		   TypedQuery<User> tQuery =
				   em.createQuery("SELECT p FROM users p WHERE p.username=?1 AND p.password=?2", User.class);
		   tQuery.setParameter(1, pUser);
		   tQuery.setParameter(2, pPass);
		   
		   User p;
		   
		   try {
			   p = tQuery.getSingleResult();
			} catch (NoResultException e) {
				System.out.println("Try again");	
				return false;
			}
		   		  
		   User user = em.find( User.class, p.getUserId() );
		   System.out.println( user.toString() );
		   user.setFirstname(pSession);
		   //after update
		   System.out.println( user.toString() );
		   em.getTransaction( ).commit( );
		   em.close();
		   
		   if (p.getPassword().equals(pPass)) {
		   System.out.print("Auth successfull");
		   return true;		  
		   }
		return false;
	   }
	   
	   public api.User getUserInfo(String pUser) {
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		   EntityManager em = emfactory.createEntityManager( );
		   em.getTransaction( ).begin( );
		   TypedQuery<User> tQuery =
				   em.createQuery("SELECT p FROM users p WHERE p.username=?1", User.class);
		   tQuery.setParameter(1, pUser);
		   
		   User p;

		   try {
			   p = tQuery.getSingleResult();
			} catch (NoResultException e) {
				System.out.println("No Result");	
				return null;
			}

		   api.User user = new api.User(p.getUserId(),p.getUsername(),p.getMUser(),p.getName(),p.getPassword(),p.getRole(),p.getRowFlag(),p.getCUser(),p.getEmail(),p.getFirstname(),p.getCTs(),p.getCreateTime(),p.getMTs());
		   System.out.print("getUserInfo successfull");
		   return user; 
	   }
	   
	   public List<api.User> allUser() {
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		   EntityManager em = emfactory.createEntityManager( );
		   em.getTransaction( ).begin( );
		   TypedQuery<User> tQuery =
				   em.createQuery("SELECT p FROM users p ", User.class);
		   List<User> p;
		   try {
			   p = tQuery.getResultList();
			} catch (NoResultException e) {
				System.out.println("No Result");	
				return null;
			}
		   System.out.print("getUserAllInfo successfull");
		   List<api.User> resultlist = new ArrayList<>();
		   
		   for (User px: p )
		   {
		   resultlist.add(new api.User(px.getUserId(),px.getUsername(),px.getMUser(),px.getName(),px.getPassword(),px.getRole(),px.getRowFlag(),px.getCUser(),px.getEmail(),px.getFirstname(),px.getCTs(),px.getCreateTime(),px.getMTs()));
		   }
		   System.out.print("getUserInfo successfull");
		   return resultlist; 
	   }

	public boolean createUser(String username, String mail, String name, String pass, String role) {
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );

	     User user = new User();
	      
		user.setUsername(username);
		user.setEmail(mail);
		user.setName(name);
		user.setPassword(pass);
		user.setRole(role);
		
	     entitymanager.persist(user);
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( ); 
	     
	     System.out.print("createUser successfull");
	     return true;
	}

	public boolean deleteUser(Integer userId) {
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );	      
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );
	      User User = entitymanager.find( User.class, userId );
	      entitymanager.remove(User);
	      entitymanager.getTransaction().commit();
		  System.out.print("deleteUser successfull");
		  return true;
	}

	public boolean updateUser(api.User r) {

	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );
	          
	     User user = entitymanager.find( User.class, r.getUserId());
		 Date date= new Date();     
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 
			//User.setUserId(pUser.getUserId());
			user.setUsername(r.getUsername());
			user.setEmail(r.getEmail());
			user.setName(r.getName());
			user.setPassword(r.getPassword());
			user.setRole(r.getRole());
			user.setMTs(ts);
			user.setCTs(ts);
			user.setCreateTime(ts);
			
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close();
	     emfactory.close();
	    
	     System.out.print("updateUser successfull");
	     return true;
	}

}
