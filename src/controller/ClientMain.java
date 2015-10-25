package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ClientMain {

	public static void main(String[] args) throws UnknownHostException {
		 String YourName1="Client";
	     int port1 = 28411;
	     Messeger m = null;
		 InetAddress addr = InetAddress.getByName("127.1.0.1");
		 Caller c=new Caller(addr,port1,YourName1);
         try {
			 m = new Messeger(c.Connector());
		} catch (IOException e) {
			System.out.println("MAIN PROBLEM.");
		}
		assert m != null;
		m.Send("Hello bitch");
	}

}
