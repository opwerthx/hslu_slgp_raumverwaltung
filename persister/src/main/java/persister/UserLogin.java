package persister;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogin implements Serializable {

	private static final long serialVersionUID = 2087551531556192267L;

		public UserLogin(){
 		      
	   }
	   

	   public boolean checkUserLogin(String pUser, String pPass, String pSession) {
			EntityManager em = JPAUtil.createEntityManager();
			   em.getTransaction( ).begin( );
		  try {
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
		
	   } catch (Exception e) {
			System.out.print(" >> Auth Exec failed: "+ e);
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		
	   }
	   
	   public api.User getUserInfo(String pUser) {
			EntityManager em = JPAUtil.createEntityManager();
		   try {
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
	   } catch (Exception e) {
				System.out.print(" >> Auth Exec failed: "+ e);
				throw e;
			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}
			
	   }
	   
	   public List<api.User> allUser() {
			EntityManager em = JPAUtil.createEntityManager();
			   em.getTransaction( ).begin( );
			   try {
		   TypedQuery<User> tQuery =
				   em.createQuery("SELECT p FROM users p ", User.class);
		   List<User> p;
		   try {
			   p = tQuery.getResultList();
			} catch (NoResultException e) {
				System.out.println("No Result");	
				return null;
			}

		   List<api.User> resultlist = new ArrayList<>();
		   
		   for (User px: p )
		   {
		   resultlist.add(new api.User(px.getUserId(),px.getUsername(),px.getMUser(),px.getName(),px.getPassword(),px.getRole(),px.getRowFlag(),px.getCUser(),px.getEmail(),px.getFirstname(),px.getCTs(),px.getCreateTime(),px.getMTs()));
		   }
		   System.out.print("getUserAllInfo successfull");
		   return resultlist; 
		   
	   } catch (Exception e) {
			System.out.print(" >> getUserAllInfo failed: "+ e);
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	   }

	public boolean createUser(String username, String mail, String name, String pass, String role) {
		EntityManager em = JPAUtil.createEntityManager();
		   em.getTransaction( ).begin( );
	     try {
	     User user = new User();
	      
		user.setUsername(username);
		user.setEmail(mail);
		user.setName(name);
		user.setPassword(pass);
		user.setRole(role);
		
	     em.persist(user);
	     em.getTransaction( ).commit( );
	     System.out.print("createUser successfull");
	     return true;
	     
	 } catch (Exception e) {
			System.out.print(" >> createUser failed: "+ e);
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	public boolean deleteUser(Integer userId) {
		EntityManager em = JPAUtil.createEntityManager();
		   em.getTransaction( ).begin( );
		   try {
	      User User = em.find( User.class, userId );
	      em.remove(User);
	      em.getTransaction().commit();
		  System.out.print("deleteUser successfull");
		  return true;
		   } catch (Exception e) {
				System.out.print(" >> deleteUser failed: "+ e);
				throw e;
			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}
	}

	public boolean updateUser(api.User r) {

		EntityManager em = JPAUtil.createEntityManager();
		   em.getTransaction( ).begin( );
	          try {
	     User user = em.find( User.class, r.getUserId());
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
			
	     em.getTransaction( ).commit( );
	     System.out.print("updateUser successfull");
	     return true;
	     
	          } catch (Exception e) {
					System.out.print(" >> updateUser failed: "+ e);
					throw e;
				} finally {
					if (em.isOpen()) {
						em.close();
					}
				}
	}

}
