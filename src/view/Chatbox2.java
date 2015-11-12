package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chatbox2 extends JFrame {
    JButton send = new JButton("Send");
    JTextArea ma = new JTextArea();
    JScrollPane message = new JScrollPane(ma);
    final int TA_ROWS = 20;
    final int TA_COLS = 35;
    JTextArea text = new JTextArea(TA_ROWS, TA_COLS);

    public Chatbox2() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {


        Timer t;

        JFrame chat = new JFrame("ChatApp v0.1");
        chat.setResizable(false);


        JLabel conv_with = new JLabel("Conversation with: ");
        JLabel l = new JLabel();
        JTextField nick = new JTextField("", 20);

        JButton off = new JButton("Disconnect");
        JPanel panel = new JPanel();
        JPanel panel_foot = new JPanel();

        text.setEditable(false);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        nick.setEditable(false);

        chat.setLayout(new BorderLayout());
        chat.setSize(new Dimension(600, 400));
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
        chat.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chat.add(panel, BorderLayout.NORTH);
        chat.add(panel_foot, BorderLayout.AFTER_LAST_LINE);
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
        panel_foot.add(send, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        panel_foot.add();
    }

    public static void main(String args[]) {
        new Chatbox2();
    }
}

