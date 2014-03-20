package p4;

import java.rmi.RemoteException;

/**
 * Created by kristine on 20/03/14.
 */
public class TimeoutThread extends Thread {
	public TimeoutThread(ServerImpl si, Resource r, int resourceID, int transactionID) {
		try {
			this.sleep(Globals.TIMEOUT_INTERVAL);
			if (r.getLockOwner() == transactionID) {
				System.out.println("Transaction "+transactionID+" trying to unlock resource "+resourceID);
				boolean result = si.releaseLock(transactionID, resourceID);
				if (result) {System.out.println("Transaction "+transactionID+" unlocked resource "+resourceID);}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
