package p4;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by kristine on 20/03/14.
 */
public class ProbeThread extends Thread {
	public ProbeThread(Map<Integer, Server> servers, List<Integer> serverIds, int transactionOwner) throws RemoteException{
		servers.get(transactionOwner).probe(serverIds);
	}
}
