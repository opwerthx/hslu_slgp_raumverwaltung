package api;

import java.rmi.Remote; 
import java.rmi.RemoteException;  

public interface ClientInt extends Remote{
	String RO_NAME = "CLIENT_RO";
	public void tell (String name)throws RemoteException ;
	public String getName()throws RemoteException ;
}
