package view;

import Model.Matiere;
import Model.User;

import javax.swing.*;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"/*new SyntheticaPlainLookAndFeel()*/);
        } catch(Exception e){e.printStackTrace();}
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                  //  new LoginFrame();
                new ManageFrame("aaa");
            }
        });
    }

}
