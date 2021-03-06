

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Vector;

public class Chatbox {
    JFrame newFrame = new JFrame("Chat v0.1");
    Chatbox mainGUI;
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
    ServerConnection server;


    public void ChatBox() {
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(MyLookAndFeel.class.getCanonicalName());

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
            System.out.println("NICHT");
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
        in_call.setVisible(true);
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
        try {
            newFrame.setVisible(false);
        } catch (Exception e) {
        }

        preFrame = new JFrame("Choose your username!(chat v0.2");
        preFrame.setLocationRelativeTo(null);
        usernameChooser = new JTextField(30);
        IPFriend = new JTextField(30);
        JLabel chooseUsernameLabel = new JLabel("Pick a username:");
        JLabel EnterIP = new JLabel("Enter IP:");
        JButton enterServer = new JButton("Enter Chat Server");
        JButton ApplyName = new JButton("Apply");
        JButton list = new JButton("Contacts");
        JPanel prePanel = new JPanel(new GridBagLayout());

        prePanel.setBackground(new Color(149, 188, 214));

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
        prePanel.add(list, preRight);

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
        list.addActionListener(new listButtonListener());
    }

    class listButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            show_list();
        }
    }

    public void show_list() {
        if (server != null) {

            PrintWriter pw = null;
            File fn=new File("friends.txt");
            if (!fn.exists()){
            	try {
					fn.createNewFile();
				} catch (IOException e) {
				}
            }
            try {
				pw=new PrintWriter(fn);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Object[] headers = {"Name", "Ip"};
            String[] names = server.getAllNicks();
            Vector<String> namesOnline = new Vector(); //names
            for (int i = 0; i < names.length; i++) {
                if (server.isNickOnline(names[i]))
                    namesOnline.add(names[i]);
            }


            Vector<String> ipUsers = new Vector(); //ip
            for (int i = 0; i < namesOnline.size(); i++) {
                ipUsers.add(server.getIpForNick(namesOnline.get(i)));
            }
            
            Object[][] data = new Object[namesOnline.size()][2];
            for (int i = 0; i < namesOnline.size(); i++) {
                data[i][0] = namesOnline.get(i);
                data[i][1] = ipUsers.get(i);
            }
           
            for (int i=0;i <namesOnline.size();i++){
            	pw.println(namesOnline.get(i)+"   "+ipUsers.get(i));
            	pw.flush();
            }
            
            
            JTable jTabPeople;

            JFrame jfrm = new JFrame("Contacts");
            jfrm.getContentPane().setLayout(new BorderLayout());
            jfrm.setSize(new Dimension(200, 100));
            jfrm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jfrm.setLocationRelativeTo(preFrame);
            jTabPeople = new JTable(data, headers);
            jfrm.add(new JScrollPane(jTabPeople));
            jfrm.getContentPane().add(jTabPeople);
            jfrm.setVisible(true);
        }
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

        chatBox.setLineWrap(false);
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

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15)); // TODO nice fonts
        off.addActionListener(new offButtonListener());
        sendMessage.addActionListener(new sendMessageButtonListener());
        newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        newFrame.setSize(850, 300);
        newFrame.setLocationRelativeTo(null);

        /*messageBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (messageBox.getText().length() > 1) {
                    GregorianCalendar d = new GregorianCalendar();
                    String mes = messageBox.getText();
                    System.out.println(mes);
                    chatBox.append(d.getTime() + "<" + username + ">:  " + mes
                            + "\n"); // date and time

                    messageBox.setText("");
                }
            }
        });*/ //TODO fix it
    }

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("SEND BUTTON WAS PRESSED");
            if (messageBox.getText().length() < 1) {
                // do nothing
            } else if (messageBox.getText().equals(".clear")) {
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                System.out.println("SEND BUTTON WAS PRESSED 2");
                GregorianCalendar d = new GregorianCalendar();
                String mes = messageBox.getText();
                System.out.println(mes);
                chatBox.append(d.getTime() + "<" + username + ">:  " + mes
                        + "\n"); // date and time

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
            //newFrame = new JFrame("Chat v0.1");
            closeAll();
            preDisplay();

            mr.connect.disconnect();
            mr = null;

        }

    }

    class enterNickButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            server = new ServerConnection();
            try{
            server.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server");
            server.connect();
            server.setLocalNick(username);
            server.goOnline();
            }
            catch (Exception e){
            	System.out.println("Can't connect to the server now");
            }
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
                String addr = null;

                addr = ip;

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

                    preFrame.setVisible(false);
                    display();
                    mr.n = chatBox;
                }
                if (b == 2) {
                    error();
                }
            }
        }
    }

}
