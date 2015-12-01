package view;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class MyLookAndFeel extends BasicLookAndFeel
{
    public String getDescription ()
    {
        return "Cross-platform Java Look and Feel";
    }

    public String getName ()
    {
        return "MyLookAndFeel";
    }

    public String getID ()
    {
        return getName ();
    }

    public boolean isNativeLookAndFeel ()
    {
        return false;
    }

    public boolean isSupportedLookAndFeel ()
    {
        return true;
    }
}