
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chatbox {

    Chatbox mainGUI;
    JFrame newFrame = new JFrame("Chat v0.1");
    JButton sendMessage;
    JTextField messageBox;
    JTextArea chatBox;
    JTextField usernameChooser;
    JFrame preFrame;
    JTextField IPFriend;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Chatbox mainGUI = new Chatbox();
        mainGUI.preDisplay();
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


        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.add(BorderLayout.EAST, ApplyName);

        preFrame.setVisible(true);
        preFrame.setResizable(false);
        preFrame.setSize(300, 300);

        preFrame.pack();

        enterServer.addActionListener(new enterServerButtonListener());
    }

    public void display() {
        newFrame.setVisible(true);
        JPanel southPanel = new JPanel();
        newFrame.add(BorderLayout.SOUTH, southPanel);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        sendMessage = new JButton("Send Message");
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        newFrame.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;
        right.weightx = 2.0;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
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
                chatBox.append("<" + username + ">:  " + messageBox.getText() + "\n");
                messageBox.setText("");
            }
        }
    }

    String username;
    String ip;

    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            ip = IPFriend.getText();
            if (username.length() < 1) {
                System.err.println("No!");
            } else if (ip.length() < 1) {
                System.err.println("No!");
            } else {
                preFrame.setVisible(false);
                display();
            }
        }

    }

    class ApplyListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            if (username.length() > 3 && ip.length() > 6) {
                System.out.println("OK");
                display();
            } else {
                System.out.println("Choose another name or IP");
            }

        }
    }

}
