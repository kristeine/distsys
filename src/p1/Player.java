package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 04/02/14.
 */
public interface Player extends Remote {
	
	//Looks for a player object in the registry given by the url
	public Player lookup(String url) throws RemoteException;
	
	//Gives your opponent a reference to your Player object
	public boolean connect(Player player) throws RemoteException;
	
	//Notifies the opponent that a cell has been edited
	public void setCell(int x, int y, char mark) throws RemoteException;
	
	//Disconnects and unbinds this Player from registry
	public void disconnect() throws RemoteException;
	
	//Getters
	public String getName() throws RemoteException;
	public char getMark() throws RemoteException;
	
}
