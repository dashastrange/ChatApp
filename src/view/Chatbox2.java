package view;

import javax.swing.*;
import java.awt.*;

public class Chatbox2 extends JFrame {

    public static void main(String[] args) {
        JFrame chat = new JFrame("ChatApp v0.1");
        JTextField text = new JTextField(10);
        JTextArea ma = new JTextArea();
        JScrollPane message = new JScrollPane(ma);
        JLabel conv_with = new JLabel("Conversation with: ");
        JTextField nick = new JTextField(20);
        JButton send = new JButton("Send");
        JButton off = new JButton("Disconnect");
        JPanel panel = new JPanel();
        JPanel panel_foot = new JPanel();

        text.setEditable(false);
        nick.setEditable(false);

        chat.setLayout(new BorderLayout());
        chat.setSize(new Dimension(600, 400));
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
        chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chat.add(panel, BorderLayout.NORTH);
        chat.add(panel_foot, BorderLayout.SOUTH);
        panel_foot.setFocusable(true);
        panel_foot.setLayout(new GridBagLayout());

        panel.add(conv_with, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        panel.add(nick, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        panel.add(off, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        chat.add(text, BorderLayout.CENTER);
        chat.add(message, BorderLayout.SOUTH);





    }

}