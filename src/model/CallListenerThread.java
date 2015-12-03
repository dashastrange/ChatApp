


import java.net.Socket;

public class CallListenerThread extends Thread {
	CallListener cl;
	Thread t;
	int a = 0;
	boolean isSearch = true;
	Socket socket1 = null;
	Chatbox notmy;
	String username = "Unnamed";

	CallListenerThread(Chatbox x) {
		notmy = x;
		t = new Thread(this);
		cl = new CallListener();
		t.start();
	}

	public void run() {
		while (isSearch == true) {
			while (notmy.username == "Unnamed") {
				try {
					t.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			cl.YourName = notmy.username;
			socket1 = cl.WaitForConnection();
			if (notmy.mr != null)
				if (notmy.mr.isNeed == false)
					notmy.mr = null;
			if (notmy.mr == null)
				cl.isOK = true;
			System.out.println("SERVER. isOK ="+cl.isOK);
			if (cl.isOK == true) {
				notmy.incomingConnection();
				while (notmy.ch == 0) {
					try {
						t.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (notmy.ch == 1) {
					notmy.closeAll();
					notmy.whenCalled();
					notmy.mr = new MessageReciever(cl.socket, notmy,
							cl.FriendName);
					notmy.mr.connect.Accepted();
					cl.isOK = false;
				}
				if (notmy.ch == 2) {
					notmy.mr = new MessageReciever(cl.socket, notmy,
							cl.FriendName);
					notmy.mr.connect.Rejected();
					notmy.mr = null;
				}
			}
			else {
				notmy.mr = new MessageReciever(cl.socket, notmy,
						cl.FriendName);
				notmy.mr.connect.out.println("Busy");
				notmy.mr.connect.out.flush();
				notmy.mr = null;
			}
		}

	}

	public Socket getSocket() {
		return socket1;
	}
}
