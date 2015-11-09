package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Caller {
	public String YourName;
	public String FriendName;
	public boolean isOK = true;
	public int port = 28411;
	InetAddress ip;
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String str;

	Caller(InetAddress ip, int port, String YourName) {
		this.port = port;
		this.ip = ip;
		this.YourName=YourName;
		

	}

	public Socket Connector() throws IOException {
		try {
			 socket = new Socket("127.1.0.1", port);
		} catch (IOException e1) {
		
		}
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
		}
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
		} catch (IOException e) {
		}

		try {
			FriendName = in.readLine();
			
		} catch (IOException e) {
			System.out.println("33333");
		}
		if (FriendName.startsWith("NO")) {
			
			System.out.println("false");
			return null;
		}
		try{
			System.out.println(YourName);
			out.println(YourName);
			out.flush();
		}
		catch ( Exception e){
			System.out.println("1010");
		}
		try {
		
			out.println("Hello "+FriendName);
			out.flush();
		}
		catch (Exception e){
			
		}
		try {
			str=in.readLine();
	
		} catch (IOException e) {
		
		}
		if (str.startsWith("He")) {
			System.out.println(true);
			return socket;
		}
		else {
			System.out.println(false);
			return null;
		}
			
	}

}
