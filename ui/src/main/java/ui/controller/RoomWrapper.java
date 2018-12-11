package ui.controller;

import api.Room;

public class RoomWrapper {

	private Room room;

	public RoomWrapper() {

	}

	public RoomWrapper(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Integer getRoomId() {
		return room.getRoomId();
	}
	public void setRoomId(Integer roomId) {
		room.setRoomId(roomId);
	}
	public Integer getRoomMinUtil() {
		return room.getRoomMinUtil();
	}
	public void setRoomMinUtil(Integer roomMinUtil) {
		room.setRoomMinUtil(roomMinUtil);
	}
	public Integer getRoomMaxUtil() {
		return room.getRoomMaxUtil();
	}
	public void setRoomMaxUtil(Integer roomMaxUtil) {
		room.setRoomMaxUtil(roomMaxUtil);
	}
	public String getRoomName() {
		return room.getRoomName();
	}
	public void setRoomName(String roomName) {
		room.setRoomName(roomName);
	}
	public String getRoomEquipment() {
		return room.getRoomEquipment();
	}
	public void setRoomEquipment(String roomEquipment) {
		room.setRoomEquipment(roomEquipment);
	}
	public String getRoomLocation() {
		return room.getRoomLocation();
	}
	public void setRoomLocation(String roomLocation) {
		room.setRoomLocation(roomLocation);
	}

}	