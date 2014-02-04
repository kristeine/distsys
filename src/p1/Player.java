package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 04/02/14.
 */
public interface Player extends Remote {
	public Player lookup(String url) throws RemoteException;
	public boolean connect(TicTacToePlayer player) throws RemoteException;
	public void disconnect() throws RemoteException;
}
