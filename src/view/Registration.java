package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {
    public static void main(String[] args) {
        Registration regGUI = new Registration();
        regGUI.start();
    }

    public void start() {
        JFrame registr = new JFrame();
        JLabel welcome = new JLabel("Welcome to ChatApp!");
        JLabel new_u = new JLabel("New user? You can register!");
        JLabel new_name = new JLabel("Choose name");
        JTextField new_name_field = new JTextField(10);
        JLabel pass = new JLabel("Choose password");
        JPasswordField pass_field = new JPasswordField(10);
        JButton reg = new JButton("Registration");

        JLabel logg = new JLabel("...or you can enter chat!");
        JButton log = new JButton("Login");

        registr.setLayout(new GridBagLayout());
        registr.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        registr.setVisible(true);
        registr.setSize(new Dimension(300, 200));
        registr.setLocationRelativeTo(null);
        registr.setResizable(true);

        registr.setLayout(new GridBagLayout());
        registr.add(welcome, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(new_u, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(new_name, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(new_name_field, new GridBagConstraints(1, 1, 1, 2, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(pass, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(pass_field, new GridBagConstraints(1, 2, 1, 2, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(reg, new GridBagConstraints(3, 1, 1, 2, 0.0, 0.9, GridBagConstraints.EAST,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0) );
        registr.add(logg, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        registr.add(log, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        registr.pack();

        new_name_field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
