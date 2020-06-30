package view;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Model.*;
public class ListeAbs extends JPanel implements ActionListener {
    private AppFrame frame;
    private JTextField textField;
    private JTable table;
    private JButton deleteBtn;
    private JButton mailBtn;
    private JButton searchBtn;
    private JSpinner spinner;
    private JScrollPane scrollPane;
    private String lastDate = "";
    private String lastName = "";
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

        deleteBtn = new JButton("Effacer Absence");
        selectPanel.add(deleteBtn, "cell 5 0,growy");
        deleteBtn.addActionListener(this);

        JLabel dateLabel = new JLabel("Selectionner une date :");
        selectPanel.add(dateLabel, "cell 2 1,alignx right");

         spinner = new JSpinner();
        spinner.setModel(new SpinnerDateModel(new Date(1592953200000L), null, null, Calendar.LONG_FORMAT));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner , "dd/MM/yyyy");
        spinner.setEditor(editor);
        selectPanel.add(spinner, "cell 3 1,growx");

         mailBtn = new JButton("Envoyer Mail");
        selectPanel.add(mailBtn, "cell 5 1,grow");

        searchBtn = new JButton("Rechercher");
        selectPanel.add(searchBtn, "cell 3 2,growx");
        searchBtn.addActionListener(this);

         scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchBtn){
            lastName = textField.getText();
            lastDate =  new SimpleDateFormat("yyyy-MM-dd").format(spinner.getValue());
            insertTab(lastName,lastDate);
        }
        if (e.getSource() == deleteBtn){
            int row = table.getSelectedRow();
            int numSe = Integer.parseInt((String)table.getValueAt(row,0));
            String date = (String)table.getValueAt(row,1);
            DB.delete("absence" , new String[][]{{"numseance" , "=" , String.valueOf(numSe)} , {"date_abs" , "=" , date}});
            insertTab(lastName,lastDate);
        }
        if(e.getSource() == mailBtn){
            int row = table.getSelectedRow();
            int numSe = Integer.parseInt((String)table.getValueAt(row,0));
            try {
                ResultSet rs = DB.get("select email, nom , prenom from etudiant, absence where etudiant.id_etudiant = absence.id_etudiant and numseance="+numSe+";");
                rs.next();
                String mail = rs.getString("email");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Util.envoyer("absence" , "" ,mail );
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    void insertTab(String nom , String date){
        ResultSet myRs;ResultSet count;
        int x =0;
        try{
            if(!nom.isEmpty()){

                myRs = DB.get("SELECT numseance , date_abs , etudiant.nom nom_etudiant , enseignant.nom nom_enseignant, matiere.nom_matiere\n" +
                        " FROM absence , etudiant , enseignant , matiere \n" +
                        " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                        " and absence.id_matiere = matiere.id_matiere and date_abs = '"+ date+"' and" +
                        " etudiant.nom = '"+nom+"' ;");

                count = DB.get("SELECT count(*)\n" +
                        " FROM absence , etudiant , enseignant , matiere \n" +
                        " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                        " and absence.id_matiere = matiere.id_matiere and date_abs = '"+date+"' and " +
                        "etudiant.nom = '"+nom+"' ;");
                count.next();
                x = Integer.parseInt(count.getString("count(*)"));
            }else{
                myRs = DB.get("SELECT numseance , date_abs , etudiant.nom nom_etudiant , enseignant.nom nom_enseignant, matiere.nom_matiere\n" +
                        " FROM absence , etudiant , enseignant , matiere \n" +
                        " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                        " and absence.id_matiere = matiere.id_matiere and date_abs = '"+ date+"';");
                count = DB.get("SELECT count(*)\n" +
                        " FROM absence , etudiant , enseignant , matiere \n" +
                        " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                        " and absence.id_matiere = matiere.id_matiere and date_abs = '"+date+"';");
                count.next();
                x = Integer.parseInt(count.getString("count(*)"));
            }
            String[][] donnees = new String[x][5];
            for(int i = 0; i<x; i++){
                myRs.next();
                donnees[i][0] = myRs.getString("numseance");
                donnees[i][1] = myRs.getString("date_abs");
                donnees[i][2] = myRs.getString("nom_etudiant");
                donnees[i][3] = myRs.getString("nom_enseignant");
                donnees[i][4] = myRs.getString("nom_matiere");
            }
            String[] entetes = {"numseance" , "date_abs", "nom_etudiant", "nom_enseignant" ,"nom_matiere"};
            table = new JTable(donnees, entetes);
            scrollPane.setViewportView(table);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
