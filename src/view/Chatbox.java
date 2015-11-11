package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Chatbox extends JFrame
{
    JPanel jp;
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

        jp=new JPanel();
        jp.setLayout(new GridLayout(2,1));
        l=new JLabel();
        jp.add(l);

        t=new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                // If the user isn't typing, he is thinking
                if(!typing)
                    l.setText("Thinking..");
            }
        });

        t.setInitialDelay(2000);

        jt=new JTextField();
        jp.add(jt);

        add(jp,BorderLayout.SOUTH);

        jt.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke)
            {
                l.setText("You are typing..");
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
        if(text.trim().isEmpty()) return;

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