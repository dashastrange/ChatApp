package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Messeger {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	boolean sended = false;

	Messeger(Socket s) {
		if (s != null)
			this.s = s;

		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
		}
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					s.getOutputStream())), true);
		} catch (IOException e) {
		}

	}

	public void Send(String mes) {
		out.println("Message"); // ////////////��� ������� ���������, ��� ������
								// ���������
		out.println(mes);
		out.flush();
		if (sended = true) {
		}// ////////////// ����� � ��������
			// ///////////// ������ � ����.
		else {
		}// ////////////// ��������� �� �����������
		
		sended = false;
	}

	public void Recieved(String mes) {
		System.out.println("message:"+mes);
		// out.println("SENDED");
	}

}
