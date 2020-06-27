package view;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

import java.util.Date;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class ListeAbs extends JPanel{
    private AppFrame frame;
    private JTextField textField;
    private JTable table;

    public ListeAbs() {
        frame=new AppFrame("Liste d'Absences", 650,550,false);

        frame.getContentPane().add(this, BorderLayout.CENTER);
        setLayout(new BorderLayout(0, 0));

        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(new TitledBorder(null, "Selectionner", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(selectPanel, BorderLayout.NORTH);
        selectPanel.setLayout(new MigLayout("", "[][45.00][82.00][171.00][29.00][43.00][][53.00]", "[29.00][31.00][]"));

        JLabel searchLabel = new JLabel("Chercher par nom :");
        selectPanel.add(searchLabel, "cell 2 0,alignx right");

        textField = new JTextField();
        selectPanel.add(textField, "cell 3 0,growx");
        textField.setColumns(10);

        JButton deleteBtn = new JButton("Effacer Absence");
        selectPanel.add(deleteBtn, "cell 5 0,growy");

        JLabel dateLabel = new JLabel("Selectionner une date :");
        selectPanel.add(dateLabel, "cell 2 1,alignx right");

        JSpinner spinner = new JSpinner();
        spinner.setModel(new SpinnerDateModel(new Date(1592953200000L), null, null, Calendar.DAY_OF_YEAR));
        selectPanel.add(spinner, "cell 3 1,growx");

        JButton mailBtn = new JButton("Envoyer Mail");
        selectPanel.add(mailBtn, "cell 5 1,grow");

        JButton searchBtn = new JButton("Rechercher");
        selectPanel.add(searchBtn, "cell 3 2,growx");

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

    }

}
