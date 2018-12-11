package api;

import java.io.Serializable;
import java.rmi.Remote;

public class Room implements Serializable, Remote {


	private static final long serialVersionUID = -3193626730577781313L;
	private Integer roomId;
	private Integer roomMinUtil;
	private Integer roomMaxUtil;
	private String roomEquipment;
	private String roomLocation;
	private String roomName;

    public Room() {
		//roomId = -1;
	}

    public Room( 
    		Integer roomId,
    		Integer roomMinUtil,
    		Integer roomMaxUtil,
    		String roomEquipment,
    		String roomLocation,
    		String roomName
    		) 
    {	 	
		 this.roomId = roomId;
		 this.roomMinUtil = roomMinUtil;
		 this.roomMaxUtil = roomMaxUtil;
		 this.roomEquipment = roomEquipment;
		 this.roomLocation = roomLocation;
		 this.roomName = roomName;
    }

	public Room(Object object, Object object2, Object object3, Object object4, Object object5, Object object6) {
		 this.roomId = (Integer)  object;
		 this.roomMinUtil = (Integer) object2;
		 this.roomMaxUtil = (Integer) object3;
		 this.roomEquipment = object4.toString();
		 this.roomLocation = object5.toString();
		 this.roomName = object6.toString();
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getRoomMinUtil() {
		return roomMinUtil;
	}

	public void setRoomMinUtil(Integer roomMinUtil) {
		this.roomMinUtil = roomMinUtil;
	}

	public Integer getRoomMaxUtil() {
		return roomMaxUtil;
	}

	public void setRoomMaxUtil(Integer roomMaxUtil) {
		this.roomMaxUtil = roomMaxUtil;
	}

	public String getRoomEquipment() {
		return roomEquipment;
	}

	public void setRoomEquipment(String roomEquipment) {
		this.roomEquipment = roomEquipment;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


}