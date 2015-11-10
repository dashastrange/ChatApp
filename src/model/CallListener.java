package model;

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

public class CallListener {
	public String YourName = "Server";
	public String FriendName;
	public boolean isOK = true;
	public int port = 28411;
	InetAddress ip;
	ServerSocket s;
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String str;

	CallListener() {
		try {
			s = new ServerSocket(port);
		} catch (IOException e1) {
		}
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
		}

	}

	public Socket WaitForConnection() {
		System.out.println("waiting for a client");
		try {
			socket = s.accept();
		} catch (IOException e) {

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
		System.out.println("got client");
		if (isOK == false) {
			out.println("NO");

			return null;
		} else {
			try {

				out.println(YourName);
				out.flush();
				FriendName = in.readLine();

			} catch (IOException e1) {

			}
			try {
				str = in.readLine();

			} catch (IOException e) {

			}
			if (str.startsWith("He")) {

				out.println("Hello " + FriendName);
				out.flush();
				isOK = false;
				System.out.println(true);
				return socket;
			} else {

				System.out.println(false);
				return null;
			}

		}

	}

}
