package p4;

import java.rmi.RemoteException;

/**
 * Created by kristine on 20/03/14.
 */
public class TimeoutThread extends Thread {
	public TimeoutThread(ServerImpl si, int resourceID, int transactionID) {
		try {
			this.sleep(Globals.TIMEOUT_INTERVAL);
			si.releaseLock(transactionID, resourceID);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
