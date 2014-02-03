package p1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 03/02/14.
 * Define everything that's available for other processes
 */
public interface TicTacRemoteInterface extends Remote {
	public String connect(String playerName, TicTacToePlayer opponent) throws RemoteException;
	public void disconnect() throws RemoteException;
	public void setCell(int x, int y, char mark);
}
