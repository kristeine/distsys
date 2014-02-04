package p1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by kristine on 03/02/14.
 */
public class TicTacToePlayer extends UnicastRemoteObject implements Player{
	String url;
	public String playerName;
	TicTacToe game;
	TicTacToePlayer opponent;
	private int playerNumber;
	public String status;

	TicTacToePlayer (String playerName, String url) throws RemoteException{
		this.playerName = playerName;
		this.url = url;

		System.out.println(playerName+": doing lookup");
		this.opponent = lookup(url);

		//Remote opponent = lookup(url);
		if (opponent != null) {
			System.out.println(playerName+": opponent "+opponent.playerName);
			//connect to opponent and give notice
			opponent.connect(this);
			playerNumber = 1;
			this.status = playerName + " waiting";
		} else {
			// bind, passively wait for opponent, set mark
			bindUrl();
			playerNumber = 0;
			this.status = playerName + " waiting";
		}
	}

	public TicTacToePlayer lookup(String url) throws RemoteException{
		TicTacToePlayer oppo = null;
		try {
			oppo = (TicTacToePlayer) Naming.lookup(url);
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return oppo;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCell(int x, int y, char mark) {
		//do something?
	}

	public boolean connect(TicTacToePlayer player) {
		this.opponent = player;  //listeners?
		return true;
	}

	public boolean bindUrl() {
		boolean check = true;
		try {
			Naming.rebind(this.url, this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			check = false;
		} catch (RemoteException e) {
			e.printStackTrace();
			check = false;
		}
		return check;
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
