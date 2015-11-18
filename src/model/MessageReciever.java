package model;

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
    JTextArea chat;
    String nick;


    public MessageReciever(Socket socket2, JTextArea chatBox, String friendName) {
        t = new Thread(this);
        this.nick = friendName;
        this.chat = chatBox;
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


    public void run() {
        while (isNeed == true) {

            String command;
            try {
                command = in.readLine();
                System.out.println(command);
                if (command != null) {
                    int a = prot.whichone(command);
                    if (a == 1) {
                        String mes = in.readLine();
                        connect.recieve(mes);
                        if (chat == null) System.out.println("HELLO");
                        chat.append("< " + nick + " >:  " + mes + "\n");
                    } else if (a == 2) {
                        System.out.println("Disconnect");
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