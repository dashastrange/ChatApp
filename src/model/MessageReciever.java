package model;

import view.Chatbox;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReciever extends Thread {
    Socket socket;
    public Connection connect;
    BufferedReader in;
    boolean isNeed = true;
    Protocol prot = new Protocol();
    Thread t;
    Chatbox chatn;
    JTextArea n;
    String nick;


    public MessageReciever(Socket socket2, Chatbox chatBox, String friendName) {
        t = new Thread(this);
        this.nick = friendName;
        chatn = chatBox;
        n = chatn.chatBox;
        if (socket2 != null)
            this.socket = socket2;
        try {
            in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        } catch (IOException e) {
            System.out.println("111");
        }
        connect = new Connection(socket);
        t.start();
    }

    public MessageReciever(Socket socket2, JTextArea chatBox, String friendName) {
        t = new Thread(this);
        this.nick = friendName;
        n = chatBox;
        if (socket2 != null)
            this.socket = socket2;
        if (socket2 == null) System.out.println("SOCKET NULL");
        try {
            in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        } catch (IOException e) {
            System.out.println("111");
        }
        connect = new Connection(socket);
        t.start();
    }


    public void run() {
        while (isNeed) {

            String command;
            try {
                command = in.readLine();
                System.out.println(command);
                if (command != null) {
                    int a = prot.whichone(command);
                    if (a == 1) {
                        String mes = in.readLine();
                        connect.recieve(mes);
                        if (n == null) System.out.println("HELLO");
                        n.append("< " + nick + " >:  " + mes + "\n");
                    } else if (a == 2) {
                        System.out.println("Disconnect");
                        chatn.closeAll();
                        chatn.preDisplay();
                        connect.disconnect();
                        socket = null;
                        isNeed = false;
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