package persister;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ManageBooking {
	public void Booking(){

	}   

	public List<api.Booking> allBookings() {
		EntityManager em = JPAUtil.createEntityManager();
		try {
		em.getTransaction( ).begin( );
			   TypedQuery<Booking> tQuery =
					   em.createQuery("SELECT b FROM Booking b ", Booking.class);
			   List<persister.Booking> p;
			   try {
				   p = tQuery.getResultList();
				} catch (NoResultException e) {
					System.out.println("No Result");	
					return null;
				}
			   
		        List<api.Booking> resultlist = new ArrayList<>();
			   for (Booking px: p )
			   {
				   resultlist.add(new api.Booking(
				    		px.getBookingId(), 
				    		px.getRoom().getRoomId(), 
				    		px.getBookingUtil(), 
				    		px.getRowFlag(), 
				    		px.getUser().getUsername(), 
				    		px.getCUser(),
				    		px.getMUser(), 
				    		px.getBkFrom(),
				    		px.getBkTo(),
				    		px.getCTs(), 
				    		px.getMTs(),
				    		px.getRoom().getRoomName()
				    		));
			   }
					System.out.print("getAllBookings successfull");
					return resultlist; 
				} catch (Exception e) {
					System.out.print(" >> getAllBookings failed: "+ e);
					throw e;
				} finally {
					if (em.isOpen()) {
						em.close();
					}
				}

		   }
		   	
	public List<api.Booking> findBooking(Integer pID) {
		EntityManager em = JPAUtil.createEntityManager();
	    em.getTransaction( ).begin( );
	    try {
	    Booking px = em.find( Booking.class, pID );
	    
	    List<api.Booking> resultlist = new ArrayList<>();
	    resultlist.add(new api.Booking(
	    		px.getBookingId(), 
	    		px.getRoom().getRoomId(), 
	    		px.getBookingUtil(), 
	    		px.getRowFlag(), 
	    		px.getUser().getUsername(), 
	    		px.getCUser(),
	    		px.getMUser(), 
	    		px.getBkFrom(), 
	    		px.getBkTo(),
	    		px.getCTs(), 
	    		px.getMTs()
	    		));
		System.out.print("findBookings successfull");
				
		
			return resultlist; 
		} catch (Exception e) {
			System.out.print(" >> findBookings failed: "+ e);
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	public String createBooking(api.Booking pBooking) throws RemoteException {
		EntityManager em = JPAUtil.createEntityManager();
	    em.getTransaction( ).begin( );

		try {
   		 System.out.print(pBooking.toString());
	     Booking booking = new Booking( );
	     
			Date date= new Date();     
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			booking.setBkFrom(pBooking.getBkFrom());
			booking.setBkTo(pBooking.getBkTo());
			booking.setBookingUtil(pBooking.getBookingUtil());
			booking.setCTs(ts);
			booking.setMTs(ts);
			booking.setCUser(pBooking.getcUser());
			booking.setMUser(pBooking.getmUser());
			booking.setRowFlag(pBooking.getRowFlag());
			booking.setRowFlag("Hinzugefügt");
			
			TypedQuery<User> tQuery =
					   em.createQuery("SELECT p FROM users p WHERE p.username=?1", User.class);
			   tQuery.setParameter(1, pBooking.getBooking_for());
			   User user;
			   user = tQuery.getSingleResult();
	     
	     System.out.print("getRoom start");
	     Room room = em.find( Room.class, pBooking.getRoomId());
	     
	     booking.setRoom(room);
	     booking.setUser(user);
	     System.out.print("persist start");
	     em.persist( booking );
	     em.getTransaction( ).commit( );

			System.out.print("createBooking successfull");
			return "createBooking successfull";
		} catch (Exception e) {
			System.out.print(" >> createBooking failed: "+ e);
			throw e;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}
	
	public String cancelBooking(Integer pID, String pmUser) throws RemoteException {
		EntityManager em = JPAUtil.createEntityManager();
	    em.getTransaction( ).begin( );
		try {
	      Booking booking = em.find( Booking.class, pID );
	      
		  Date date= new Date();     
		  long time = date.getTime();
		  Timestamp ts = new Timestamp(time);
	      
	      booking.setRowFlag("STORNIERT");
	      booking.setMUser(pmUser);
	      booking.setMTs(ts);
	      em.getTransaction( ).commit( );
	      
	      //after update
	      System.out.println( booking );
		  
	
				  System.out.print("cancelBooking successfull");
				  return "cancelBooking successfull";
			} catch (Exception e) {
				System.out.print(" >> cancelBooking failed: "+ e);
				throw e;
			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}
	}
}
