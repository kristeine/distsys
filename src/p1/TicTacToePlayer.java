package p1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by kristine on 03/02/14.
 */
public class TicTacToePlayer implements Remote{
	String url;
	String playerName;
	TicTacToe game;
	TicTacToePlayer opponent;

	TicTacToePlayer (String playerName, String url) {
		this.playerName = playerName;
		this.url = url;
		// do naminglookup
		// if not in use, bind, passively wait for opponent
		// else connect to opponent and give notice
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCell(int x, int y, char mark) {
		//do something?
	}

	public String connect(String playerName, TicTacToePlayer opponent) throws RemoteException {
		try {
			Naming.rebind(this.url, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return this.playerName;
	}

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
