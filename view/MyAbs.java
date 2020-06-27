package view;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTable;

public class MyAbs extends JPanel{
    private AppFrame frame;
    private JTable table;

    public MyAbs() {
        frame= new AppFrame("Mes Absences",550,450,false);

        frame.getContentPane().add(this, BorderLayout.CENTER);

        table = new JTable();
        this.add(table);
    }
}