package persister;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ManageRoom {
	
	public void Room(){

	}   
	
	public List<api.Room> getRooms() {
			   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
			   EntityManager em = emfactory.createEntityManager( );
			   em.getTransaction( ).begin( );
			   TypedQuery<Room> tQuery =
					   em.createQuery("SELECT b FROM Room b ", Room.class);
			   List<persister.Room> p;
			   try {
				   p = tQuery.getResultList();
				} catch (NoResultException e) {
					System.out.println("No Result");	
					return null;
				}
			   
		        List<api.Room> resultlist = new ArrayList<>();
			   for (Room px: p )
			   {
				   resultlist.add(new api.Room(
				    		px.getRoomId(),
				    		px.getRoomMinUtil(),
				    		px.getRoomMaxUtil(),
				    		px.getRoomEquipment(),
				    		px.getRoomLocation(),
				    		px.getRoomName()
				    		));
			   }
			   System.out.println(resultlist.toString());
			   System.out.print("getAllRooms successfull");
			   
			   return resultlist; 
		   }
	
	public List<api.Room> getRoom(Integer pID) {
		   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		   EntityManager em = emfactory.createEntityManager( );
		   em.getTransaction( ).begin( );
		   Room px = em.find( Room.class, pID );
		   em.getTransaction().commit();
	       List<api.Room> resultlist = new ArrayList<>();
			   resultlist.add(new api.Room(
			    		px.getRoomId(),
			    		px.getRoomMinUtil(),
			    		px.getRoomMaxUtil(),
			    		px.getRoomEquipment(),
			    		px.getRoomLocation(),
			    		px.getRoomName()
			    		));
		   System.out.println(resultlist.toString());
		   System.out.print("getAllRoom successfull");
		   
		   return resultlist; 
	   }
		   	
	public boolean createRoom(api.Room pRoom) throws RemoteException {
		
	     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
	     EntityManager entitymanager = emfactory.createEntityManager( );
	     entitymanager.getTransaction( ).begin( );

	     Room Room = new Room();
	      
 		//Room.setRoomId(pRoom.getRoomId());
 		Room.setRoomMinUtil(pRoom.getRoomMinUtil());
 		Room.setRoomMaxUtil(pRoom.getRoomMaxUtil());
 		Room.setRoomEquipment(pRoom.getRoomEquipment());
 		Room.setRoomLocation(pRoom.getRoomLocation());
 		Room.setRoomName(pRoom.getRoomName());
 		Room.setRowFlag("ACTIVE");
 
	     entitymanager.persist( Room );
	     entitymanager.getTransaction( ).commit( );
	     entitymanager.close( );
	     emfactory.close( ); 
	     
	     System.out.print("createRoom successfull");
	     return true;
	}
	
   	
public boolean updateRoom(api.Room pRoom) throws RemoteException {
	
     EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
     EntityManager entitymanager = emfactory.createEntityManager( );
     entitymanager.getTransaction( ).begin( );
          
     Room Room = entitymanager.find( Room.class, pRoom.getRoomId());

		//Room.setRoomId(pRoom.getRoomId());
		Room.setRoomMinUtil(pRoom.getRoomMinUtil());
		Room.setRoomMaxUtil(pRoom.getRoomMaxUtil());
		Room.setRoomEquipment(pRoom.getRoomEquipment());
		Room.setRoomLocation(pRoom.getRoomLocation());
		Room.setRoomName(pRoom.getRoomName());
		Room.setRowFlag("ACTIVE");
		
     entitymanager.getTransaction( ).commit( );
     entitymanager.close();
     emfactory.close();
    
     System.out.print("updateRoom successfull");
     return true;
}

	
	public boolean deleteRoom(Integer pID) throws RemoteException {
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );	      
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );
	      Room Room = entitymanager.find( Room.class, pID );
	      entitymanager.remove(Room);
	      entitymanager.getTransaction().commit();
		  System.out.print("deleteRoom successfull");
		  return true;
	}

	public List<api.Room> getOptRooms(Timestamp from, Timestamp to, Integer util) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		EntityManager em = emfactory.createEntityManager( );
		System.out.print(util);
		System.out.print(from);
		System.out.println(to);
		System.out.println( "SELECT a.room_id, a.room_min_util, a.room_max_util, a.room_equipment, a.room_location, a.room_name FROM rooms a WHERE " + 
		   		+ util +" between a.room_min_util and a.room_max_util " + 
		   		"and a.room_id not in(select b.room_id from rooms b left join bookings c on b.room_id = c.id_room " + 
		   		"where '"+ from.toString() +"' between c.bk_from and c.bk_to or '"+to.toString()+"' between c.bk_from and c.bk_to group by b.room_id)");
		javax.persistence.Query q = em.createNativeQuery(
    		    "SELECT a.room_id, a.room_min_util, a.room_max_util, a.room_equipment, a.room_location, a.room_name FROM rooms a WHERE " + 
		   		+ util +" between a.room_min_util and a.room_max_util " + 
		   		"and a.room_id not in(select b.room_id from rooms b left join bookings c on b.room_id = c.id_room " + 
		   		"where '"+ from.toString() +"' between c.bk_from and c.bk_to or '"+to.toString()+"' between c.bk_from and c.bk_to group by b.room_id)");
	     System.out.println(q.getResultList().toString());
	     
	     List<Object[]> rooms = q.getResultList();
	     List<api.Room> resultlist = new ArrayList<>();
	     for (Object[] a : rooms) {
	            resultlist.add(new api.Room(
	            		a[0],
	            		a[1],
	            		a[2],
	            		a[3],
	            		a[4], 
	            		a[5]
			    		));
	     }
	     System.out.print("getOptRoom successfull");
	     return resultlist; 
	}
	
	public List<api.Room> getStatRooms() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persister" );
		EntityManager em = emfactory.createEntityManager( );
		javax.persistence.Query q = em.createNativeQuery(
    		    "select room_id,cast(count(*) as INTEGER) as booking_count,cast(ROUND(avg(booking_util),0) as INTEGER) as avg_util,room_name,concat(room_equipment, ' ' ,room_location),case when cast(bk_from as time) between '00:00:00.000000' and '12:00:00.000000' then 'Morgen' when cast(bk_from as time) between '12:00:01.000000' and '18:00:00.000000' then 'Nachmittag'when cast(bk_from as time) between '18:00:01.000000' and '24:00:00.000000' then 'Abend'end as timeusage from rooms inner join bookings on room_id = id_room group by room_id,room_name,room_location,room_equipment,case when cast(bk_from as time) between '00:00:00.000000' and '12:00:00.000000' then 'Morgen'when cast(bk_from as time) between '12:00:01.000000' and '18:00:00.000000' then 'Nachmittag'when cast(bk_from as time) between '18:00:01.000000' and '24:00:00.000000' then 'Abend' end order by room_id,case when cast(bk_from as time) between '00:00:00.000000' and '12:00:00.000000' then 'Morgen'when cast(bk_from as time) between '12:00:01.000000' and '18:00:00.000000' then 'Nachmittag'when cast(bk_from as time) between '18:00:01.000000' and '24:00:00.000000' then 'Abend'end ,count(*),avg_util");
	     
	     List<Object[]> rooms = q.getResultList();
	     List<api.Room> resultlist = new ArrayList<>();
	     for (Object[] a : rooms) {
	            resultlist.add(new api.Room(
	            		a[0],
	            		a[1],
	            		a[2],
	            		a[3],
	            		a[4], 
	            		a[5]
			    		));
	     }
	     System.out.print("getStatRoom successfull");
	     return resultlist; 
	}
}
