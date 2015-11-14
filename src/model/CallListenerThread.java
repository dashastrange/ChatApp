import java.net.Socket;

public class CallListenerThread extends Thread {
	CallListener cl;
	Thread t;
	int a = 0;
	boolean isSearch = true;
	Socket socket1 = null;

	CallListenerThread() {
		t = new Thread(this);
		cl = new CallListener();
		t.start();
	}

	public void run() {
		while (isSearch == true) {
			socket1 = cl.WaitForConnection();
			if (cl.mr != null) {
				if (cl.mr.isNeed = false)
					cl.isOK = true;
			}
			if (cl.isOK == true) {
				cl.mr = new MessageReciever(cl.socket);
				cl.isOK = false;
			}
		}

	}

	public Socket getSocket() {
		return socket1;
	}
}
