package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) throws UnknownHostException {
		String YourName1 = "Client";
		int port1 = 28411;
		Connection con = null;

		InetAddress addr = InetAddress.getByName("127.1.0.1");
		Caller c = new Caller(addr, port1, YourName1);
		try {
			 con = new Connection(c.Connector());
		} catch (IOException e) {
		}
	     con.mes.Send("Hello");
	     con.Accepted();

	}
}