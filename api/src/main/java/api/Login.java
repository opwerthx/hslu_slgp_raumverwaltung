package api;

import java.rmi.Remote; 
import java.rmi.RemoteException;

// Creating Remote interface for our application 
public interface Login extends Remote { 
   String RO_NAME = "LOGIN_RO";
	public boolean login (ClientInt a)throws RemoteException ;
	public void publish (String s)throws RemoteException ;
	//public Vector getConnected() throws RemoteException ;
}