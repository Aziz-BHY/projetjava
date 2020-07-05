package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import Model.DB;
import Model.User;
import controller.Messages;
import net.miginfocom.swing.MigLayout;
import sun.security.util.ArrayUtil;
import Model.Util;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AbsenceManager extends JPanel implements ActionListener {
    private AppFrame frame;
    private JTable classTable;
    private JTable absTable;
    private JButton importBtn;
    private JButton absBtn;
    private JButton saveBtn;
    private JPanel classPanel;
    private JScrollPane ClassTab;
    private JScrollPane AbsTab;
    private ArrayList<Integer> AbsListe;
    private JButton unAbsBtn;
    private User user;
    private String lastSearch;
    private JComboBox classCombo;
    private JTextField textnum;
    String[] entetes = {"id_etudiant", "nom", "prenom", "e_mail"};

    private String[][] donnees1 = new String[0][4];

    public AbsenceManager(User user) throws SQLException {
        this.user = user;

        frame = new AppFrame("Gerer les absences des classes", 650, 500, false);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel choicePanel = new JPanel();
        choicePanel.setBorder(new TitledBorder(null, "Mes Classes ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        frame.getContentPane().add(choicePanel, BorderLayout.NORTH);
        choicePanel.setLayout(new MigLayout("", "[grow][][79.00,grow][197.00,grow][11.00][][][10.00][grow]", "[]"));

        JLabel classLabel = new JLabel("Classe :");
        choicePanel.add(classLabel, "cell 2 0,alignx right");

        ResultSet rs = DB.get("Select filiére, niveau , nom_matiere , correspondance.id_matiere from correspondance , classe , matiere\n" +
                "where id_enseignant = 2 and correspondance.id_classe = classe.id_classe and correspondance.id_matiere = matiere.id_matiere;");
        int nb = DB.count("*", "correspondance", new String[][]{{"id_enseignant", "=", String.valueOf(user.id)}});
        String[] array = new String[nb + 1];
        array[0] = "----------";
        for (int i = 1; i <= nb; i++) {
            if (rs.next())
                array[i] = rs.getString("filiére") + "-" + rs.getString("niveau") + "-" + rs.getString("nom_matiere") + "-" + rs.getString("id_matiere");
        }
        classCombo = new JComboBox(array);
        choicePanel.add(classCombo, "cell 3 0,growx");

        JLabel numseance = new JLabel("numero seance :");
        choicePanel.add(numseance, "cell 2 1,alignx right");

        textnum = new JTextField();
        choicePanel.add(textnum, "cell 3 1,alignx left ,width 20:30:50 ");

        importBtn = new JButton("Importer");
        choicePanel.add(importBtn, "cell 5 0,growx");
        importBtn.addActionListener(this);
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
        classTable = new JTable();
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
        if (e.getSource() == absBtn) {
            int[] row = classTable.getSelectedRows();
            String[][] donnees2 = new String[0][4];
            for (int i = 0; i < row.length; i++) {
                int j = 0;
                if (!AbsListe.contains(Integer.parseInt((String) classTable.getValueAt(row[i], 0)))) {
                    System.out.println("ouiii");
                    String[][] newString = new String[1][4];
                    newString[0][0] = (String) classTable.getValueAt(row[i], 0);
                    newString[0][1] = (String) classTable.getValueAt(row[i], 1);
                    newString[0][2] = (String) classTable.getValueAt(row[i], 2);
                    newString[0][3] = (String) classTable.getValueAt(row[i], 3);
                    donnees2 = Util.concat(donnees2, newString, 4);
                    j++;
                    AbsListe.add(new Integer(Integer.parseInt((String) classTable.getValueAt(row[i], 0))));
                }
            }
            if (!donnees2.equals(new String[0][4]))
                donnees1 = Model.Util.concat(donnees1, donnees2, 4);
            absTable = new JTable(donnees1, entetes);
            AbsTab.setViewportView(absTable);
        }
        else if (e.getSource() == unAbsBtn) {
            int row = absTable.getSelectedRow();
            if(donnees1.length>0) {
                String[][] newDonnees = new String[donnees1.length - 1][4];
                int val2 = Integer.parseInt((String) absTable.getValueAt(row, 0));
                int k = 0;
                for (int i = 0; i < donnees1.length; i++) {
                    if (i != row) {
                        newDonnees[k] = donnees1[i];
                        k++;
                    } else AbsListe.remove(new Integer(val2));

                }
                donnees1 = newDonnees;
                absTable = new JTable(donnees1, entetes);
                AbsTab.setViewportView(absTable);
            }
        }
        else if (e.getSource() == importBtn) {
            absTable = new JTable();
            AbsTab.setViewportView(absTable);
            lastSearch = (String) classCombo.getSelectedItem();

            try {
                String[] classe = lastSearch.split("-");
                ResultSet rs = null;
                rs = DB.get("select id_etudiant , nom , prenom , email from etudiant \n" +
                        "where id_classe = (select id_classe from classe where filiére = '" + classe[0] + "' and niveau ='" + classe[1] + "' );");
                ResultSet rs1 = DB.get("select count(*) from etudiant \n" +
                        "where id_classe = (select id_classe from classe where filiére = '" + classe[0] + "' and niveau ='" + classe[1] + "' );");
                rs1.next();
                int x = Integer.parseInt(rs1.getString("count(*)"));
                String[][] donnees = new String[x][4];
                int i = 0;
                while (rs.next()) {
                    donnees[i][0] = rs.getString("id_etudiant");
                    donnees[i][1] = rs.getString("nom");
                    donnees[i][2] = rs.getString("prenom");
                    donnees[i][3] = rs.getString("email");
                    i++;

                }
                classTable = new JTable(donnees, entetes);
                ClassTab.setViewportView(classTable);

            } catch (SQLException ex) {
                Messages.showError(1);
            } catch (Exception ex) {
                Messages.showMessage("veuillez choisir une classe");
            }


        }
        else if (e.getSource() == saveBtn) {
            String num = textnum.getText();
            try {
                if (!num.isEmpty()&& Integer.parseInt(num) != 0) {
                    ResultSet rs = DB.get("*", "absence", new String[][]{{"numseance", "=", num},
                            {"id_matiere ", "=", lastSearch.split("-")[3]}});

                    if (!rs.next())
                    {
                        int nb = AbsListe.size();
                        if (nb > 0) {
                            for (int i = 0; i < nb; i++) {
                            DB.insert("INSERT INTO absence (date_abs , id_etudiant , id_enseignant , id_matiere) VALUES (sysdate() , " + AbsListe.get(i).toString() + " ,  " + String.valueOf(user.id) + " , " + lastSearch.split("-")[3] + ")");
                            }
                        }
                        frame.dispose();
                    }else Messages.showMessage("ce numero de séance existe deja");
                }else Messages.showMessage("numero de séance doit un nombre");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
}
