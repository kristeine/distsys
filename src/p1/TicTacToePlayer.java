package p1;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by kristine on 03/02/14.
 */
public class TicTacToePlayer extends UnicastRemoteObject implements Player{
	String url;
	int portNr;
	public String playerName;
	TicTacToe game;
	Player opponent;
	public String status;
	private char mark;
	private boolean myTurn;
	

	TicTacToePlayer (String playerName, String url, int portNr, TicTacToe game) throws RemoteException{
		this.playerName = playerName;
		this.url = url;
		this.game = game;

		System.out.println(playerName+": doing lookup");
		this.opponent = lookup(url);

		//Remote opponent = lookup(url);
		if (opponent != null) {
			System.out.println(playerName+": opponent "+ opponent.getName());
			//connect to opponent and give notice
			opponent.connect(this);
			mark = 'O';
			myTurn = true;
			this.status = playerName + " waiting";
		} else {
			// bind, passively wait for opponent, set mark
			bindUrl();
			mark = 'X';
			myTurn = false;
			this.status = playerName + " waiting";
		}
	}

	public Player lookup(String url) throws RemoteException{
		Player player = null;
		try
		{
			Registry registry = LocateRegistry.getRegistry("localhost", 3090);
			player = (Player) registry.lookup(url);
		} catch (NotBoundException e){
			//do nothing
		} catch (ConnectException e)
		{
			//no nothing
		}

		return player;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCell(int x, int y, char mark) {
		System.out.println("setting cell");
		myTurn = !myTurn;
		game.valueChanged(x, y, mark);
	}

	public boolean connect(Player player) {
		this.opponent = player;
		System.out.println("connected...");
		return true;
	}

	public boolean bindUrl() {
		boolean check = true;
			System.out.println("Lager registry");
			try{
				Registry registry;
				registry = LocateRegistry.createRegistry(3090);
				registry.rebind(url, this);
			} catch (AccessException e){
				e.printStackTrace();
			} catch (RemoteException e){
				e.printStackTrace();
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
	
	public void notifyOpponent(int x, int y) throws RemoteException{
		try{
		opponent.setCell(x,y, this.mark);
		} catch( UnmarshalException e)
		{}
	}
	
	public char getMark() throws RemoteException {
		return this.mark;
	}
	
	public boolean myTurn(){
		return this.myTurn;
	}
	
	public void setMyTurn(boolean turn){
		this.myTurn = turn;
	}
}
