package persister;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.Room;

class ManageRoomTest {

	static Room room = null;
	static Room room2 = null;
	static Room room3 = null;
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@Test
	static void testCreateRoom() {
		
		Room room = new Room();
		Room room2 = new Room();
		Room room3 = new Room();
		
		 room.setRoomId(1234);
	     room.setRoomLocation("S12");
	     room.setRoomName("Niklaus Wirth");
	     room.setRoomMaxUtil(30);
	     room.setRoomMinUtil(1);
	     ManageRoom plist = new ManageRoom();
	     boolean resultvar;
			try {
				resultvar = plist.createRoom(room);
			     assertEquals(true, resultvar);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 room2.setRoomId(12345);
	     room2.setRoomLocation("S122");
	     room2.setRoomName("Niklaus Wirth2");
	     room2.setRoomMaxUtil(302);
	     room2.setRoomMinUtil(12);

			try {
				resultvar = plist.createRoom(room2);
			     assertEquals(true, resultvar);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 room3.setRoomId(123456);
	     room3.setRoomLocation("S123");
	     room3.setRoomName("Niklaus Wirth3");
	     room3.setRoomMaxUtil(303);
	     room3.setRoomMinUtil(13);

			try {
				resultvar = plist.createRoom(room3);
			     assertEquals(true, resultvar);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testCreateRoom();
	}

	@AfterEach
	void tearDown() throws Exception {
		testDeleteRoom();
	}
	public void  testDeleteRoom() {
		
		ManageRoom plist = new ManageRoom();
		boolean resultvar;
		try {
			resultvar = plist.deleteRoom(1234);
			assertEquals(resultvar, true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			resultvar = plist.deleteRoom(12345);
			assertEquals(resultvar, true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resultvar = plist.deleteRoom(123456);
			assertEquals(resultvar, true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
       }


	@Test
	void testGetRooms() {
		ManageRoom roomget = new ManageRoom();
		List<api.Room> plist;
		plist = roomget.getRooms();
		assertEquals(false, plist.isEmpty());
	}

	@Test
	void testGetRoom() {
		ManageRoom roomget = new ManageRoom();
		List<api.Room> plist;
		plist = roomget.getRoom(room.getRoomId());
		assertEquals(room.getRoomId(), plist.get(0).getRoomId());
	}


	@Test
	void testUpdateRoom() {

		try {
			ManageRoom roomget = new ManageRoom();
			List<api.Room> asdf;
			asdf = roomget.getRoom(1234);
			room = asdf.get(0);
			room.setRoomName("TEST");
			boolean resultvar = roomget.updateRoom(room);
			assertEquals(true, resultvar);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ManageRoom getter = new ManageRoom();
		List<api.Room> asdf = new ArrayList<>();
		asdf = getter.getRoom(room.getRoomId().intValue());
		
		assertEquals("TEST", asdf.get(0).getRoomName());	
	}


	@Test
	void testGetStatRooms() {
		Date date= new Date();     
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		Timestamp ts1 = new Timestamp(time);
		ManageRoom plist = new ManageRoom();
		List<api.Room> asdf = new ArrayList<>();
		asdf = plist.getStatRooms();
		assertNotNull(asdf);
	}

}
