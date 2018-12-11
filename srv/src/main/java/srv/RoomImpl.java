package srv;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import api.RMI_Room;
import api.Room;
import persister.ManageRoom;

public class RoomImpl extends UnicastRemoteObject implements RMI_Room {
	
	private static final long serialVersionUID = -4783646022821816323L;	
	private List<Room> roomList;
	protected RoomImpl(List<Room> list) throws RemoteException {
        super();
        this.roomList = list;
    }	    
    
    @Override
	public List<api.Room> getRoom(Integer pRoom) throws RemoteException {
		ManageRoom room = new ManageRoom();
		List<api.Room> plist;
		plist = room.getRoom(pRoom);
		return plist ;
	}

	public List<api.Room> getRooms() throws RemoteException {
			ManageRoom room = new ManageRoom();
			List<api.Room> plist = new ArrayList<>();
			plist = room.getRooms();
			return plist ;
	}

	public boolean createRoom(Room room) throws RemoteException {
		ManageRoom plist = new ManageRoom();
		return plist.createRoom(room);
	}
	
	public boolean updateRoom(Room room) throws RemoteException {
		ManageRoom plist = new ManageRoom();
		return plist.updateRoom(room);
	}


	@Override
	public boolean deleteRoom(Integer roomId) throws RemoteException {
		ManageRoom plist = new ManageRoom();
		return plist.deleteRoom(roomId);
	}

	@Override
	public List<api.Room> getOptRooms(Timestamp from, Timestamp to, Integer util) throws RemoteException {
		ManageRoom plist = new ManageRoom();
		return plist.getOptRooms(from, to, util);
	}

	@Override
	public List<Room> getStatRooms() throws RemoteException {
		ManageRoom plist = new ManageRoom();
		return plist.getStatRooms();
	}

}
