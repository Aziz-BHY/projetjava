package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import sun.security.util.ArrayUtil;
import model.Util;
import java.awt.event.*;
import java.util.ArrayList;


public class AbsenceManager extends JPanel implements ActionListener {
    private AppFrame frame;
    private JTable classTable;
    private JTable absTable;
    private JButton importBtn;
    private JButton  absBtn;
    private JButton  saveBtn;
    private JPanel classPanel;
    private JScrollPane ClassTab;
    private JScrollPane AbsTab;
    private ArrayList<Integer> AbsListe;
    private JButton unAbsBtn;
    String[] entetes = {"id_etudiant" , "nom", "prenom" , "classe"};
    String[][] donnees = {
            {"2" , "aziz" , "ben hadj yahia", "LFI"},
            {"3" , "azazaz3" , "ben az yahia", "LFI"},
            {"4" , "azazaz4" , "ben az yahia", "LFI"},
            {"5" , "azazaz5" , "ben az yahia", "LFI"}
    };
    private String[][] donnees1 = new String[0][4];

    public AbsenceManager() {

        frame=new AppFrame("Gerer les absences des classes",650,500,true);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel choicePanel = new JPanel();
        choicePanel.setBorder(new TitledBorder(null, "Mes Classes ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        frame.getContentPane().add(choicePanel, BorderLayout.NORTH);
        choicePanel.setLayout(new MigLayout("", "[grow][][79.00,grow][197.00,grow][11.00][][][10.00][grow]", "[]"));

        JLabel classLabel = new JLabel("Classe :");
        choicePanel.add(classLabel, "cell 2 0,alignx right");

        JComboBox classCombo = new JComboBox();
        choicePanel.add(classCombo, "cell 3 0,growx");

         importBtn = new JButton("Importer");
        choicePanel.add(importBtn, "cell 5 0,growx");

        JPanel actionsPanel = new JPanel();
        frame.getContentPane().add(actionsPanel, BorderLayout.CENTER);
        actionsPanel.setLayout(new MigLayout("", "[273.00,grow][5.00][grow]", "[grow]"));

         classPanel = new JPanel();
        actionsPanel.add(classPanel, "cell 0 0,grow");
        classPanel.setLayout(new BorderLayout(0, 0));

         absBtn = new JButton("Marquer Absent");
        absBtn.addActionListener(this);
        classPanel.add(absBtn, BorderLayout.NORTH);

        ClassTab = new JScrollPane();
        classTable =  new JTable(donnees , entetes);
        ClassTab.setViewportView(classTable);
        classPanel.add(ClassTab, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        actionsPanel.add(separator, "cell 1 0");

        JPanel absPanel = new JPanel();
        actionsPanel.add(absPanel, "cell 2 0,grow");
        absPanel.setLayout(new BorderLayout(0, 0));

         saveBtn = new JButton("Sauvegarder");
        absPanel.add(saveBtn, BorderLayout.SOUTH);
        saveBtn.addActionListener(this);


         unAbsBtn = new JButton("Retirer de la liste d'absences");
        unAbsBtn.addActionListener(this);
        absPanel.add(unAbsBtn, BorderLayout.NORTH);

        AbsTab = new JScrollPane();
        absTable = new JTable();
        AbsTab.setViewportView(absTable);
        absPanel.add(AbsTab, BorderLayout.CENTER);

        AbsListe = new ArrayList<Integer>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == absBtn){
            int[] row = classTable.getSelectedRows();
            String[][] donnees2 = new String[0][4];
            for(int i = 0 ;i<row.length;i++){
                int j = 0;
                if(!AbsListe.contains(Integer.parseInt((String)classTable.getValueAt(row[i],0)))){
                    System.out.println("ouiii");
                    String[][] newString = new String[1][4];
                    newString[0][0] = (String)classTable.getValueAt(row[i],0);
                    newString[0][1] = (String)classTable.getValueAt(row[i],1);
                    newString[0][2] = (String)classTable.getValueAt(row[i],2);
                    newString[0][3] = (String)classTable.getValueAt(row[i],3);
                    donnees2 = Util.concat(donnees2,newString , 4);
                    j++;
                    AbsListe.add(new Integer(Integer.parseInt((String)classTable.getValueAt(row[i],0))));
                    System.out.println(AbsListe);
                }
            }
            if(!donnees2.equals(new String[0][4]))
            donnees1 = Model.Util.concat(donnees1 , donnees2, 4);
            absTable = new JTable(donnees1 , entetes);
            AbsTab.setViewportView(absTable);
        }
        if(e.getSource() == unAbsBtn){
            int row = absTable.getSelectedRow();
            String[][] newDonnees = new String[donnees1.length-1][4];
            int val2 = Integer.parseInt((String)classTable.getValueAt(row,0));
            System.out.println(val2);
            int k=0;
            for(int i=0; i<donnees1.length; i++){
                if(i != row) {
                    newDonnees[k] = donnees1[i];
                    k++;
                }
                else AbsListe.remove(new Integer(val2));

            }
            System.out.println(AbsListe);
            donnees1 = newDonnees;
            absTable = new JTable(donnees1 , entetes);
            AbsTab.setViewportView(absTable);
        }
    }
}
