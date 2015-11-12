package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Chatbox extends JFrame
{
    JPanel main;
    JTextField jt;
    JTextArea ta;
    JLabel l;
    boolean typing;
    Timer t;

    public Chatbox()
    {
        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        setTitle("Chatbox");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        main = new JPanel();
        main.setLayout(new GridLayout(2,1));
        l = new JLabel();
        main.add(l);

        t = new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                if(!typing)
                    l.setText("Thinking..");
            }
        });

        t.setInitialDelay(2000);

        jt=new JTextField();
        main.add(jt);

        add(main,BorderLayout.SOUTH);

        jt.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke)
            {
                l.setText("Typing..");
                t.stop();
                typing=true;

                if(ke.getKeyCode()==KeyEvent.VK_ENTER) showLabel(jt.getText());
            }

            public void keyReleased(KeyEvent ke)
            {
                typing=false;

                if(!t.isRunning())

                    t.start();
            }
        });

        ta=new JTextArea();
        ta.setEditable(false);
        ta.setMargin(new Insets(7,7,7,7));
        JScrollPane js=new JScrollPane(ta);
        add(js);

        addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent we)
            {
                jt.requestFocus();
            }
        });

        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showLabel(String text)
    {
        if(text.trim().isEmpty()) {
            return;
        }

        ta.append(text+"\n");
        jt.setText("");
        l.setText("");
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                new Chatbox();
            }
        });
    }
}