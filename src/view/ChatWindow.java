package view;

import com.sun.deploy.panel.JSmartTextArea;

import javax.swing.*;
import java.awt.*;

public class ChatWindow {
    public static void main(String[] args) {
        JFrame chat = new JFrame("Chat App");
        chat.setSize(new Dimension(680, 560));
        chat.setVisible(true);
        chat.setResizable(true);
        chat.setLocationRelativeTo(null);
        chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chat.setLayout(new GridBagLayout());

        JLabel conv_with = new JLabel("Conversation with: ");
        JTextField fr_nick = new JTextField(10);
        JButton disconnect = new JButton("Disconnect");
        JTextPane chat_w = new JTextPane();
        JTextArea chat_area = new JTextArea();
        JButton send = new JButton("Send");

        chat.add(conv_with, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        chat.add(fr_nick, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        chat.add(disconnect, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        chat.add(chat_w, new GridBagConstraints(0, 2, 3, 3, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        chat.add(chat_area, new GridBagConstraints(0, 3, 3, 3, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));



    }
}
