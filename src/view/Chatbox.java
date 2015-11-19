package view;

import model.Caller;
import model.MessageReciever;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.Gregorian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

public class Chatbox {
    Chatbox mainGUI;
    JFrame newFrame = new JFrame("Chat v0.1");
    JButton sendMessage;
    JButton off;
    JTextArea messageBox;
    public JTextArea chatBox;
    JTextField usernameChooser;
    JFrame preFrame;
    JTextField IPFriend;
    public String username = "Unnamed";
    String ip;
    String friendname;
    JLabel in;
    JFrame f;
    Caller call;
    public int ch;
    public MessageReciever mr;


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Chatbox mainGUI = new Chatbox();
        mainGUI.preDisplay();
    }

    public void closeAll() {
        try {
            newFrame.setVisible(false);
        } catch (Exception e) {
        }

        try {
            preFrame.setVisible(false);
        } catch (Exception e) {
        }

        try {
            f.setVisible(false);
        } catch (Exception e) {
        }
    }

    public void whenUCalling() {
        preDisplay();
    }

    public void whenCalled() {
        display();
    }

    public void incomingConnection() {
        JFrame in_call = new JFrame("Incoming connection");
        JButton no = new JButton("Reject");
        JButton yes = new JButton("Acccept");
        JLabel call = new JLabel(
                "Someone wants to talk! What do you want to do?");

        in_call.setVisible(true);
        in_call.setSize(new Dimension(300, 90));
        in_call.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        in_call.setLocationRelativeTo(null);
        in_call.setLayout(new BorderLayout());
        in_call.setResizable(false);

        in_call.add(call, BorderLayout.NORTH);
        in_call.add(no, BorderLayout.SOUTH);
        in_call.add(yes, BorderLayout.CENTER);

        no.setForeground(Color.red);
        yes.setForeground(Color.GREEN);
        ch = 0;
        no.addActionListener(e -> {
            ch = 2;
            in_call.setVisible(false);
        });
        yes.addActionListener(e -> {
            ch = 1;
            in_call.setVisible(false);

        });
    }

    public void preDisplay() {
        newFrame.setVisible(false);
        preFrame = new JFrame("Choose your username!(chat v0.1");
        preFrame.setLocationRelativeTo(null);
        usernameChooser = new JTextField(30);
        IPFriend = new JTextField(30);
        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JLabel EnterIP = new JLabel("Enter IP:");
        JButton enterServer = new JButton("Enter Chat Server");
        JButton ApplyName = new JButton("Apply");
        JPanel prePanel = new JPanel(new GridBagLayout());

        GridBagConstraints preRight = new GridBagConstraints();
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        prePanel.add(EnterIP, preLeft);
        prePanel.add(IPFriend, preRight);

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);
        prePanel.add(EnterIP, preLeft);
        prePanel.add(IPFriend, preRight);

        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.add(BorderLayout.EAST, ApplyName);

        preFrame.setVisible(true);
        preFrame.setResizable(false);
        preFrame.setSize(300, 300);

        preFrame.pack();
        ApplyName.addActionListener(new enterNickButtonListener());
        enterServer.addActionListener(new enterServerButtonListener());
    }

    public void display() {
        newFrame.setVisible(true);
        JPanel southPanel = new JPanel();
        newFrame.add(BorderLayout.SOUTH, southPanel);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextArea(1, 50);
        messageBox.setLineWrap(true);
        sendMessage = new JButton("Send Message");
        off = new JButton("Disconnect");
        chatBox = new JTextArea();
        newFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        //chatBox.setLineWrap(false);
        chatBox.setMargin(new Insets(7, 7, 7, 7));
        chatBox.setAutoscrolls(true);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;
        right.weightx = 2.0;
        southPanel.add(Box.createHorizontalGlue());
        chatBox.setEditable(false);
        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);
        southPanel.add(off, left);

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        off.addActionListener(new offButtonListener());
        sendMessage.addActionListener(new sendMessageButtonListener());
        newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setLocationRelativeTo(null);
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                GregorianCalendar d = new GregorianCalendar();
                String mes = messageBox.getText();
                System.out.println(mes);
                chatBox.append(d.getTime() + "<" + username + ">:  " + mes + "\n"); //date and time
                messageBox.setText("");
                if (mr == null)
                    System.out.println("mr nil");
                if (mr.connect == null)
                    System.out.println("connect nil");
                if (mr.connect.mes == null)
                    System.out.println("mr nil");
                try {
                    mr.connect.SendMessage(mes);
                } catch (Exception e) {
                    System.out.println("NET");
                }
            }
        }
    }

    class offButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            closeAll();
            preDisplay();
            mr.connect.disconnect();
        }

    }

    class enterNickButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
        }

    }

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            ip = IPFriend.getText();
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
                preFrame.setVisible(false);
                display();
                try {
                    mr = new MessageReciever(call.Connector(), chatBox,
                            call.FriendName);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

    }

}
