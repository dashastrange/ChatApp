import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;

public class Login extends JFrame {
    private JLabel EnterLabel;
    private JTextField IP;
    private JButton addIP;
    private JButton removeIP;

    public Login() {
        super("Login");
        EnterLabel = new JLabel("Enter your IP-address: ");
        IP = new JTextField("127.0.0.13");
        addIP = new JButton("Connect");
        removeIP = new JButton("Retype IP");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        add(EnterLabel, BorderLayout.WEST);

        JPanel ipField = new JPanel(new FlowLayout());
        add(ipField, BorderLayout.CENTER);

        buttonsPanel.add(addIP);
        buttonsPanel.add(removeIP);
        ipField.add(IP);

        add(buttonsPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Login app = new Login();
        app.setVisible(true);
        app.pack();
    }

}