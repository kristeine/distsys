package p1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by kristine on 03/02/14.
 */
public class TicTacToePlayer implements TicTacRemoteInterface{
	String url;
	String playerName;
	TicTacToe game;

	TicTacToePlayer (String playerName, String url) {
		this.playerName = playerName;
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setCell(int x, int y, char mark) {
		//do something?
	}

	@Override
	public String connect(String playerName, TicTacToePlayer opponent) throws RemoteException {
		try {
			Naming.rebind(this.url, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return this.playerName;
	}

	@Override
	public void disconnect() throws RemoteException {
		try {
			Naming.unbind(this.url);
		} catch (NotBoundException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
