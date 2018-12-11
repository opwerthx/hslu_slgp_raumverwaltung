package srv;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import api.RMI_User;
import api.User;
import persister.UserLogin;

public class UserImpl implements RMI_User {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//
//	public UserImpl() throws RemoteException {
//		
//	}
//	
	public boolean checkUserLogin (String pUser, String pPass, String Session) throws RemoteException {
		
		UserLogin logon = new UserLogin();
		boolean auth;
		auth = false;
		auth = logon.checkUserLogin(pUser, pPass, Session);
		return auth;
	}
	
	public String getUserInfo (String pUser) throws RemoteException {
		
		UserLogin logon = new UserLogin();
		Object plist = logon.getUserInfo(pUser);
		return plist.toString();
		
	}

	public List<api.User> allUser() throws RemoteException {
		UserLogin logon = new UserLogin();
		List<api.User> plist = new ArrayList<>();
		plist = logon.allUser();
		return plist ;
	}
	
	
	public api.User getUser(String pUser) throws RemoteException {
		UserLogin logon = new UserLogin();
		api.User plist = logon.getUserInfo(pUser);
		return plist ;
	}


	public boolean createUser(String user,String mail, String name, String pass, String role) throws RemoteException {
		UserLogin logon = new UserLogin();
		boolean plist = logon.createUser(user,mail,name,pass,role);
		return plist;
	}

	@Override
	public boolean deleteUser(Integer userId) throws RemoteException {
		UserLogin logon = new UserLogin();
		boolean plist = logon.deleteUser(userId);
		return plist;
	}

	@Override
	public void updateUser(User r) throws RemoteException {
		UserLogin logon = new UserLogin();
		boolean plist = logon.updateUser(r);	
	}
	
}
