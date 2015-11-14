import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageReciever extends Thread {
	Socket socket;
	Connection connect;
	BufferedReader in;
	boolean isNeed = true;
	Protocol prot = new Protocol();
	Thread t;

	MessageReciever(Socket s) {
		t = new Thread(this);
		if (s != null)
			this.socket = s;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("111");
		}
		connect = new Connection(socket);
		t.start();
	}

	public void run() {
		while (isNeed == true) {

			String command;
			try {
				command = in.readLine();
				System.out.println(command);
				if (command != null) {
					int a = prot.whichone(command);
					if (a == 1){
						String mes=in.readLine();
						connect.recieve(mes);
					}
					
					else if (a == 2) {
						System.out.println("Disconnect");
						connect.disconnect();
						socket=null;
						isNeed=false;
					} else if (a == 3) {
						System.out.println("Accepted");
					} // accepted;
					else if (a == 4) {
						System.out.println("Rejected");
					} // rejected
					else if (a == 5) {
						System.out.println("Busy");
					} else if (a == 0) {
						System.out.println("UnknownCommand");
					}
				}
			} catch (IOException e) {
			}

		}
	}
}