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
		out.println("Message"); //////////////Это команда протокола, что пришло сообщение
		out.println(mes);
		out.flush();
		try {
			
			String a = in.readLine();
			if (a.endsWith("NDED")) {
				System.out.println("ALL OK");
				// //////////////// Запись в файл истории
				// //////////////// Вывод в формочку
			}
			;
		} catch (IOException e) {
		}

	}

	public void Recieved() {
		try {
			String got = in.readLine();
			out.println("SENDED");
		} catch (IOException e) {

		}
		System.out.println("ALL OK");
		// ////////////// Вывод в формочку
		// ///////////// Запись в файл.
	}

}
