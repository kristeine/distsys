package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 04/02/14.
 */
public interface Player extends Remote {
	public Player lookup(String url) throws RemoteException;
	public boolean connect(Player player) throws RemoteException;
	public void setCell(int x, int y, char mark) throws RemoteException;
	public void disconnect() throws RemoteException;
	
	public String getName() throws RemoteException;
}
