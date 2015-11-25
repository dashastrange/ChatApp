package view;

import model.MessageReciever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;

public class Chat_Box {
    Chat_Box chatGUI;
    JFrame newFrame = new JFrame("Chat v0.1");
    JButton sendMessage;
    JButton off;
    JTextArea messageBox;
    public JTextArea chatBox;
    public String username;
    JFrame mainframe;
    public MessageReciever mr;
    JFrame f;
    public int ch;
    Login l = new Login();

    public static void main(String[] args) {
        Chat_Box chatGUI = new Chat_Box();
        chatGUI.goChat();

    }

    public void goChat() {
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

        messageBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String mes = messageBox.getText();
                    GregorianCalendar d = new GregorianCalendar();
                    chatBox.append(d.getTime() + "<" + username + ">:  " + mes
                            + "\n"); // date and time
                    messageBox.setText("");
                }
            }
        });
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
            l.login();

            mr.connect.disconnect();
            mr = null;

        }

        public void closeAll() {

            try {
                newFrame.setVisible(false);

            } catch (Exception e) {
                System.out.println("NICHT");
            }

            try {

                mainframe.setVisible(false);
            } catch (Exception e) {
            }

            try {
                f.setVisible(false);
            } catch (Exception e) {
            }
        }
    }

    public void whenUCalling() {

        l.login();
    }

    public void whenCalled() {
        goChat();
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