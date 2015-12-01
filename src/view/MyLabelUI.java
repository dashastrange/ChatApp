package view;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class MyLabelUI extends BasicLabelUI {
    public void installUI(JComponent l) {
        super.installUI(l);

        JLabel label = (JLabel) l;
        label.setOpaque(false);
        label.setFocusable(true);
        label.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        label.setBackground(Color.PINK);
    }

    private static MyLabelUI instance = null;

    public static ComponentUI createUI(JComponent c) {
        if (instance == null) {
            instance = new MyLabelUI();
        }
        return instance;
    }

}
