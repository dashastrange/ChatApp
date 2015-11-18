

import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    Socket s;
    BufferedReader in;
    PrintWriter out;
    boolean flag = true;
    Protocol prot = new Protocol();
    public Messeger mes;
    Thread t;

    Connection(Socket s) {
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
        mes = new Messeger(s);

    }

    public void SendNickHello(String Nick) {
        out.println("ChatApp 2015 user " + Nick);
    }

    public void SendNickBusy(String Nick) {
        out.println("ChatApp 2015 user " + Nick + " busy");
    }

    public void Accepted() {
        out.println("Accepted");
    }

    public void Rejected() {
        out.println("Rejected");
    }

    public void SendMessage(String Message) {
        System.out.println("SENDING" + " " + Message);
        mes.Send(Message);
    }

    public void disconnect() {
        try {
            out.println("Disconnect");
            s.close();
        } catch (IOException e) {
        }
    }

    public void recieve(String mesa) {
        mes.Recieved(mesa);
    }


}
