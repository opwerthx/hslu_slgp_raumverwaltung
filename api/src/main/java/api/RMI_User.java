package api;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RMI_User extends Remote {
    String RO_NAME = "USER_RO";
    boolean checkUserLogin(String user, String pass, String Session) throws RemoteException;
    List<User> allUser() throws RemoteException;
    User getUser(String pUser) throws RemoteException;
	boolean createUser(String user, String mail, String name, String password, String role) throws RemoteException;
	boolean deleteUser(Integer userId) throws RemoteException;
	void updateUser(User r) throws RemoteException;
}
