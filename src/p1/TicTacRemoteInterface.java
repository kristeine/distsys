package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 03/02/14.
 */
public interface TicTacRemoteInterface extends Remote {
	public String connect(String playerName, TicTacToeServer opponent) throws RemoteException;
	public void disconnect() throws RemoteException;
}
