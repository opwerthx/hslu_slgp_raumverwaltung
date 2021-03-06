package ui.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.Map;

import api.Booking;
import api.RMI_Booking;
import api.RMI_Room;
import api.RMI_User;
import api.Room;
import api.User;

public class Context {
	
	private String Username;
	private String rmiServerIP = "127.0.0.1";//"10.177.3.159"; //"127.0.0.1" // 10.177.3.159
	private int rmiPort = 1099;
	/* Name der Policy-Datei (für SecurityManager) */
	private static final String POLICY_FILE_NAME = "rmi_client.policy";

	/* Config-Datei mit RMI-Serverangaben (IP-Adresse und Port-Nummer) */
	private static final String CONFIG_FILE_NAME = "rmi_client.properties";

	private static Context INSTANCE = null;

	/* Client-Stub: Referenz auf das entfernte Objekt */
	private RMI_Booking bookingRO = null;
	private RMI_User userRO = null;
	private RMI_Room roomRO = null;

	/* Mappe für Austausch von Objekten */
	private static Map<String, Booking> objectMap = new HashMap<>();
	private static Map<String, User> objectUserMap = new HashMap<>();
	private static Map<String, Room> objectRoomMap = new HashMap<>();
//	
	private Context() throws Exception {
		setServer();
		System.out.println(rmiServerIP);
		System.out.println(rmiPort);
		bookingRO = getRMIBookingRemoteObjectStub();
		userRO = getRMIUserRemoteObjectStub();
		roomRO = getRMIRoomRemoteObjectStub();
	}

	/*
	 * HELPER: Führt 'lookup' durch und holt den Client-Stub für das entfernte
	 * Objekt (Referenze auf das entfernte Objekt)
	 */
	private RMI_Booking getRMIBookingRemoteObjectStub() throws Exception{
		System.setProperty("java.security.policy", "checker.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("New Booking context");
		String booking_url = "rmi://" + rmiServerIP + ":" + rmiPort + "/" + RMI_Booking.RO_NAME;
		bookingRO = (RMI_Booking) Naming.lookup(booking_url);
		
		return bookingRO;
	}
	
	private RMI_User getRMIUserRemoteObjectStub() throws Exception{
		System.setProperty("java.security.policy", "checker.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("New User context");
		String user_url = "rmi://" + rmiServerIP + ":" + rmiPort + "/" + RMI_User.RO_NAME;
		userRO = (RMI_User) Naming.lookup(user_url);
		
		return userRO;
	}
	
	private RMI_Room getRMIRoomRemoteObjectStub() throws Exception{
		System.setProperty("java.security.policy", "checker.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		System.out.println("New Room Context");
		String room_url = "rmi://" + rmiServerIP + ":" + rmiPort + "/" + RMI_Room.RO_NAME;
		roomRO = (RMI_Room) Naming.lookup(room_url);
		
		return roomRO;
	}

	public synchronized static Context getInstance() {

		if (INSTANCE == null) {
			try {
				INSTANCE = new Context();
			} catch (Exception e) {
				System.out.println(">> Fehler: "+ e);
				throw new RuntimeException(e);
			}
		}

		return INSTANCE;
	}
	
	/* Liefert die Referenz auf das entfernte (Verwaltung) Objekt */
	public RMI_Booking getRMI_Booking() {
		return (RMI_Booking) bookingRO;
	}

	public static Map<String, Booking> getObjectMap() {
		return objectMap;
	}
	
	/* Liefert die Referenz auf das entfernte (Verwaltung) Objekt */
	public RMI_User getRMI_User() {
		return (RMI_User) userRO;
	}

	public static Map<String, User> getUserObjectMap() {
		return objectUserMap;
	}
	
	public RMI_Room getRMI_Room() {
		return (RMI_Room) roomRO;
	}

	public static Map<String, Room> getRoomObjectMap() {
		return objectRoomMap;
	}

	/* Diese Methode setzt den SecurityManager */
	private void setSecurityManager() throws IOException {

	System.setProperty("java.security.policy", "checker.policy");

	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new SecurityManager());
	}
	}
	
	private void setServer() throws IOException {
		
		String directory = System.getProperty("user.home");  
		String fileName = CONFIG_FILE_NAME;  
		String absolutePath = directory + File.separator + fileName;
		String Server = "";
		// read the content from file
		try(FileReader fileReader = new FileReader(absolutePath)) {  
		    int ch = fileReader.read();
		    while(ch != -1) {
		        Server += ((char)ch);
		        ch = fileReader.read();        
		    }
		} catch (FileNotFoundException e) {
		    // exception handling
		} catch (IOException e) {
		    // exception handling
		}
		
        String[] values = Server.split("\n");
        try {
        rmiServerIP = values[0]; //.replace("\n", "").replace("\r", "");;
        //rmiPort = Integer.parseInt(values[1].replace("\n", "").replace("\r", ""));
        } catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println(e); 
        }
        
    	System.setProperty("java.security.policy", "checker.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

	}

	public void setLoginUser(String pUser) {
		this.Username = pUser;
	}
	
	public String getLoginUser() {
		return this.Username;
	}
	
	public void setServer(String pServer) {
		this.rmiServerIP = pServer;
	}

}
