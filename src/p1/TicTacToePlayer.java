package p1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by kristine on 03/02/14.
 */
public class TicTacToePlayer extends UnicastRemoteObject implements Player{
	String url;
	public String playerName;
	TicTacToe game;
	Player opponent;
	private int playerNumber;
	public String status;

	TicTacToePlayer (String playerName, String url) throws RemoteException{
		this.playerName = playerName;
		this.url = url;

		System.out.println(playerName+": doing lookup");
		this.opponent = lookup(url);
		System.out.println(opponent);

		//Remote opponent = lookup(url);
		if (opponent != null) {
			System.out.println(playerName+": opponent "+ opponent.getName());
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

	public Player lookup(String url) throws RemoteException{
		Player player = null;
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 3090);
			player = (Player) registry.lookup("TicTac");
//			oppo = (TicTacToePlayer) Naming.lookup(url);
		} catch (NotBoundException e) {
//			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return player;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCell(int x, int y, char mark) {
		//do something?
	}

	public boolean connect(Player player) {
		this.opponent = player;  //listeners?
		System.out.println("connected...");
		return true;
	}

	public boolean bindUrl() {
		boolean check = true;
		try {
			System.out.println("Lager registry");
			Registry registry = LocateRegistry.createRegistry(3090);
			registry.rebind("TicTac", this);
//			Naming.rebind(this.url, this);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//			check = false;
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
	
	public String getName() throws RemoteException{
		return playerName;
	}
}
