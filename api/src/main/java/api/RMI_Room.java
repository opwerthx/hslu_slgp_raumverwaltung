package api;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

public interface RMI_Room extends Remote {
    String RO_NAME = "ROOM_RO";
    List<Room> getRooms() throws RemoteException;
    List<Room> getOptRooms(Timestamp from, Timestamp to, Integer Util) throws RemoteException;
    List<Room> getRoom(Integer roomId) throws RemoteException;
    List<api.Room> getStatRooms() throws RemoteException;
    boolean createRoom(Room room) throws RemoteException;
    boolean updateRoom(Room room) throws RemoteException;
    boolean deleteRoom(Integer roomId) throws RemoteException;
}
