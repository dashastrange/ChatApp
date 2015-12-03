
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MyButtonUI extends BasicButtonUI {
    public void installUI(JComponent c) {
        super.installUI(c);

        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setFocusable(true);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AbstractButton button = (AbstractButton) c;
        ButtonModel buttonModel = button.getModel();

        g2d.setPaint(new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight(),
                new Color(149, 188, 214)));
        g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 8, 8);
        g2d.setPaint(Color.BLACK);
        g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 6, 6);
        if (buttonModel.isPressed()) {
            g2d.translate(1, 1);
        }

        super.paint(g, c);
    }

    private static MyButtonUI instance = null;

    public static ComponentUI createUI(JComponent c) {
        if (instance == null) {
            instance = new MyButtonUI();
        }
        return instance;
    }
}