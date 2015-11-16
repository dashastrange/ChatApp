package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException {
		String YourName1 = "Client";
		int port1 = 28411;
		Connection con = null;
		int answer = -1;
		String messe;
		Scanner in = new Scanner(System.in);
		InetAddress addr = InetAddress.getByName("192.168.0.0");
		Caller c = new Caller(addr, port1, YourName1);
		try {
			con = new Connection(c.Connector());
		} catch (NullPointerException | IOException e) {

		}
		if (con==null) System.out.println("Busy");
		else
			while (answer != 5) {
				answer = in.nextInt();
				messe= in.nextLine();
				if (answer==1) {
					messe=in.nextLine();
					con.SendMessage(messe);
				}
				if (answer==2)
					con.Accepted();
				if (answer==3)
					con.SendNickBusy(YourName1);
				if (answer==4)
					con.Rejected();
				if (answer==5)
					con.disconnect();
			}

	}
}