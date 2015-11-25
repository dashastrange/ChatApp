package view;

import model.Caller;
import model.MessageReciever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Login {
    Login mainGUI;
    String username;
    String ip;
    JTextArea chatBox;
    JFrame mainframe = new JFrame("ChatApp");
    JTextField name_field = new JTextField(10);
    JTextField ip_field = new JTextField(10);
    Caller call;
    MessageReciever mr;

    public static void main(String[] args) {
        Login mainGUI = new Login();
        mainGUI.login();
    }

    public void login() {
        JLabel name = new JLabel("Enter your name");
        JLabel ip = new JLabel("Enter friend's ip");
        JButton apply = new JButton("Apply");
        JButton connect = new JButton("Connect");
        JButton fr_list = new JButton("list");

        mainframe.setLayout(new GridBagLayout());
        mainframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainframe.setVisible(true);
        mainframe.setSize(new Dimension(300, 100));
        mainframe.setLocationRelativeTo(null);
        mainframe.setResizable(false);

        mainframe.add(name, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(name_field, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(ip, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(ip_field, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(apply, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.9, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(connect, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.9, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        mainframe.add(fr_list, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.9, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        mainframe.pack();
        apply.addActionListener(new enterNickButtonListener());
        connect.addActionListener(new enterServerButtonListener());
    }

    public void error() {
        JFrame err = new JFrame("ERROR");
        err.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel errl = new JLabel("Canceled");
        err.add(errl);
        err.setLocationRelativeTo(null);
        err.setSize(100, 100);
        err.pack();
        err.setVisible(true);
    }


    class enterNickButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = name_field.getText();
        }

    }

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            username = name_field.getText();
            ip = ip_field.getText();
            if (username.length() < 1) {
                System.err.println("No!");
            } else if (ip.length() < 1) {
                System.err.println("No!");
            } else {
                InetAddress addr = null;
                try {
                    addr = InetAddress.getByName(ip);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                call = new Caller(addr, 28411, username);
                try {
                    mr = new MessageReciever(call.Connector(), chatBox,
                            call.FriendName);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                int b = 0;
                while (b == 0) {
                    if (mr.LastCommand.startsWith("Acce")) b = 1;
                    else if (mr.LastCommand.startsWith("Reje")) b = 2;
                    else
                        try {
                            this.wait(100);
                        } catch (Exception e) {
                        }

                }
                System.out.println("b=" + b);
                if (b == 1) {

                    mainframe.setVisible(false);
                    login();
                    mr.n = chatBox;
                }
                if (b == 2) {
                    error();
                }
            }
        }
    }

}
