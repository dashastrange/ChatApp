package view;

import model.Caller;
import model.MessageReciever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.GregorianCalendar;

public class MainFrame {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame mainGUI = new MainFrame();
            mainGUI.go_chat();
        });
    }

    MainFrame mainGUI;
    JFrame mainf = new JFrame("ChatApp");
    JPanel header = new JPanel();
    JLabel name = new JLabel("Your name: ");
    JTextField name_f = new JTextField(10);
    JButton apply = new JButton("Apply");
    JTextField ip_f = new JTextField(10);
    JLabel ip_l = new JLabel("Friend`s ip: ");
    JButton connect = new JButton("Connect");
    JLabel c_with = new JLabel("Conversation with: ");
    JTextField conv_name = new JTextField(10);
    JButton off = new JButton("Disconnect");
    JButton list = new JButton("Contacts");
    JPanel footer = new JPanel();
    public JTextArea chat_box = new JTextArea();
    JTextArea mes = new JTextArea();
    JButton send = new JButton("Send");
    public String username;
    Caller call;
    public MessageReciever mr;
    String ip;
    JFrame f;
    public int ch;



    public void go_chat() {
        conv_name.setEditable(false);
        footer.setLayout(new GridBagLayout());
        chat_box.setAutoscrolls(true);
        chat_box.setEditable(false);
        mes.setLineWrap(true);

        mainf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainf.setVisible(true);
        mainf.setSize(new Dimension(700, 400));
        mainf.setLocationRelativeTo(null);
        mainf.setLayout(new BorderLayout());

        header.setLayout(new GridBagLayout());

        mainf.add(header, BorderLayout.NORTH);
        header.add(name, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(name_f, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(apply, new GridBagConstraints(2, 0, 1, 2, 0.0, 0.9, GridBagConstraints.NORTH,
                        GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 2, -7));
        header.add(ip_l, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(ip_f, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(connect, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 2, -7));

        header.add(c_with, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(conv_name, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        header.add(off, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 2, -7));
        header.add(list, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 2, -7));


        mainf.add(new JScrollPane(chat_box), BorderLayout.CENTER);
        mainf.add(footer, BorderLayout.SOUTH);
        footer.add(new JScrollPane(mes), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 155, 55));
        footer.add(send, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 2, -7));

        off.addActionListener(new offButtonListener());
        send.addActionListener(new sendMessageButtonListener());
        apply.addActionListener(new enterNickButtonListener());
        connect.addActionListener(new enterServerButtonListener());

        mes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = mes.getText();
                    GregorianCalendar d = new GregorianCalendar();
                    chat_box.append(d.getTime() + "<" + username + ">:  " + message
                            + "\n"); // date and time
                    mes.setText("");
                }
            }
        });

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

    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("empty message didn`t send");
            if (mes.getText().length() < 1) {
                // do nothing
            } else if (mes.getText().equals(".clear")) {
                chat_box.setText("Cleared all messages\n");
                mes.setText("");
            } else {
                System.out.println("message sent");
                GregorianCalendar d = new GregorianCalendar();
                String message = mes.getText();
                System.out.println(mes);
                chat_box.append(d.getTime() + "<" + username + ">:  " + message
                        + "\n"); // date and time

                mes.setText("");
                if (mr == null)
                    System.out.println("mr nil");
                if (mr.connect == null)
                    System.out.println("connect nil");
                if (mr.connect.mes == null)
                    System.out.println("mr nil");
                try {
                    mr.connect.SendMessage(message);
                } catch (Exception e) {
                    System.out.println("no");
                }
            }
        }

    }

    class offButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainf = new JFrame("Chat v0.1");
            closeAll();
            send.setEnabled(false);

            mr.connect.disconnect();
            mr = null;
        }

    }

    class enterNickButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = name_f.getText();
        }

    }

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = name_f.getText();
            ip = ip_f.getText();
            send.setEnabled(true);
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
                    mr = new MessageReciever(call.Connector(), chat_box,
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

                    mainf.setVisible(false);
                    go_chat();
                    mr.n = chat_box;
                }
                if (b == 2) {
                    error();
                }
            }
        }
    }

    public void closeAll() {

        try {
            mainf.setVisible(false);

        } catch (Exception e) {
            System.out.println("NICHT");
        }

        try {

            mainf.setVisible(false);
        } catch (Exception e) {
        }

        try {
            f.setVisible(false);
        } catch (Exception e) {
        }
    }

    public void whenUCalling() {
        go_chat();
    }

    public void whenCalled() {
        incomingConnection();
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


}
