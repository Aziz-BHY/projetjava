package view;

import javax.swing.*;

public class App {
    public static void main(String[] args)
    {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"/*new SyntheticaPlainLookAndFeel()*/);
        } catch(Exception e){e.printStackTrace();}

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AbsenceManager();
            }
        });
    }

}
