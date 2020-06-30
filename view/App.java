package view;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.commons.math3.util.ArithmeticUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.*;
public class App {
    public static void main(String[] args) throws IOException {


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"/*new SyntheticaPlainLookAndFeel()*/);
        } catch(Exception e){e.printStackTrace();}

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    ResultSet r  = DB.get("*", "enseignant" , new String[][]{{"id_enseignant", "=" , "2"}});
                    r.next();
                    User u = new User(r,"enseignant");
                    new ListeAbs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
