package view;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(new Dimension(300, 100));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        frame.setLayout(new GridBagLayout());
        JLabel nicknameLabel = new JLabel("Enter your nick: ");
        JLabel IPLabel = new JLabel("Enter friend's IP: ");
        JButton ConnectButton = new JButton("Connect");
        JTextField nicknameTextField = new JTextField(15);
        nicknameTextField.setText("Daria");
        JTextField IPField = new JTextField(15);
        IPField.setText("14.88");
        JButton StartConversation = new JButton("Start conversation");

        frame.add(nicknameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(IPLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(ConnectButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(nicknameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(IPField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(StartConversation, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        frame.pack();
    }

}