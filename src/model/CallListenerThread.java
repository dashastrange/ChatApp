import java.net.Socket;

public class CallListenerThread extends Thread {
	CallListener cl;
	Thread t;
	int a = 0;
	boolean isSearch = true;
	Socket socket1 = null;
	Chatbox chatBox = new Chatbox();
	Chatbox notmy;

	CallListenerThread(Chatbox x) {
		notmy = x;
		t = new Thread(this);
		cl = new CallListener();
		t.start();
	}

	public void run() {
		while (isSearch == true) {
		    while(notmy.username=="Unnamed"){
		    	try {
					t.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
			cl.YourName=notmy.username;
			socket1 = cl.WaitForConnection();
			if (cl.mr != null) {
				if (cl.mr.isNeed = false)
					cl.isOK = true;
			}
			if (cl.isOK == true) {
				chatBox.incomingConnection();
				while (chatBox.ch == 0) {
					try {
						t.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (chatBox.ch == 1) {
					notmy.closeAll();
					chatBox.username=notmy.username;
					chatBox.whenCalled();
					chatBox.mr = new MessageReciever(cl.socket,chatBox.chatBox,cl.FriendName);
					cl.isOK = false;
				}
			}
		}

	}

	public Socket getSocket() {
		return socket1;
	}
}
