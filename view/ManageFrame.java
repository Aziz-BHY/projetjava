package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.DB;
import model.Matiere;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JToggleButton;

public class ManageFrame extends JPanel implements ActionListener{
	
	private JFrame frame;

	
	private JTabbedPane tabbedPane;
	private JTable table;

	private JComboBox tablesCombo;
	private JComboBox triCombo;

	private JPanel classTab;
	private JPanel userTab;
	private JPanel panel;

	private JLabel searchLabel;
	private JLabel idClassLabel;
	private JLabel filiereLabel;
	private JLabel nivLabel;
	private JLabel triLabel;
	private JLabel libelleLabel;
	private JLabel tablesLabel;

	private JScrollPane ClassTab;
	private JScrollPane UserPane;
	private JScrollPane ResultsTab;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField libelleField;
	private JTextField nivField;
	private JTextField filiereField;
	private JTextField idClassField;

	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;

	private JButton addBtn;
	private JButton deleteClassBtn;
	private JButton editInfoBtn;
	private JButton consultListBtn;
	private JButton saveChangeBtn;
	private JButton btnNewButton_4;
	private JButton btnNewButton_1;
	private JButton btnNewButton_3;
	private JButton searchBtn;
	private JButton triBtn;
	private JButton consultBtn;
	private JButton deleteBtn;

	private String lastSearch = "";

	public ManageFrame(String what) {

		frame= new AppFrame("Gerer "+what,700,560,true);
		frame.setSize(700,570);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel tabsPanel = new JPanel();
		frame.getContentPane().add(tabsPanel, BorderLayout.CENTER);
		tabsPanel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabsPanel.add(tabbedPane);
		
		ResultsTab = new JScrollPane();
		tabbedPane.addTab("Resultats Recherche", null, ResultsTab, null);
		
		table = new JTable();
		ResultsTab.setViewportView(table);
		
		ClassTab = new JScrollPane();
		tabbedPane.addTab("Class name here", null, ClassTab, null);
		
		classTab = new JPanel();
		ClassTab.setViewportView(classTab);
		classTab.setLayout(new MigLayout("", "[grow][9.00][122.00][25.00][63.00][102.00][154.00][28.00][11.00][14.00][9.00,grow]", "[33.00,grow][3.00][35.00][10.00][35.00][12.00][34.00][12.00][31.00][8.00][30.00][42.00,grow]"));
		
		consultListBtn = new JButton("Voir la liste des eleves");
		classTab.add(consultListBtn, "cell 2 2,grow");
		
		idClassLabel = new JLabel("ID de classe :");
		classTab.add(idClassLabel, "cell 5 2");
		
		idClassField = new JTextField();
		classTab.add(idClassField, "cell 6 2,growx");
		idClassField.setColumns(10);
		
		deleteClassBtn = new JButton("Supprimer Classe");
		classTab.add(deleteClassBtn, "cell 2 4,grow");
		
		libelleLabel = new JLabel("Libelle :");
		classTab.add(libelleLabel, "cell 5 4");
		
		libelleField = new JTextField();
		classTab.add(libelleField, "cell 6 4,growx");
		libelleField.setColumns(10);
		
		editInfoBtn = new JButton("Modifier Informations");
		classTab.add(editInfoBtn, "cell 2 6,grow");
		
		nivLabel = new JLabel("Niveau :");
		classTab.add(nivLabel, "cell 5 6");
		
		nivField = new JTextField();
		classTab.add(nivField, "cell 6 6,growx");
		nivField.setColumns(10);
		
		filiereLabel = new JLabel("Filiere :");
		classTab.add(filiereLabel, "cell 5 8");
		
		filiereField = new JTextField();
		classTab.add(filiereField, "cell 6 8,growx");
		filiereField.setColumns(10);
		
		saveChangeBtn = new JButton("Sauvegarder les modifications");
		classTab.add(saveChangeBtn, "cell 6 10,grow");
		
		UserPane = new JScrollPane();
		tabbedPane.addTab("Type: person name", null, UserPane, null);
		
		userTab = new JPanel();
		UserPane.setViewportView(userTab);
		userTab.setLayout(new MigLayout("", "[grow][83.00][134.00][18.00][156.00,grow][78.00][grow]", "[44.00,grow][32.00,grow][20.00][][4.00][][4.00][][4.00][][4.00][][4.00][][4.00][][grow]"));
		
		btnNewButton_1 = new JButton("Supprimer Utilisateur");
		userTab.add(btnNewButton_1, "cell 2 1,growx");
		
		btnNewButton_3 = new JButton("Modifier Utilisateur");
		userTab.add(btnNewButton_3, "cell 4 1,growx");
		
		lblNewLabel_2 = new JLabel("ID :");
		userTab.add(lblNewLabel_2, "cell 2 3");
		
		textField_1 = new JTextField();
		userTab.add(textField_1, "cell 4 3,grow");
		textField_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Nom :");
		userTab.add(lblNewLabel_3, "cell 2 5");
		
		textField_2 = new JTextField();
		userTab.add(textField_2, "cell 4 5,grow");
		textField_2.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Prenom :");
		userTab.add(lblNewLabel_4, "cell 2 7");
		
		textField_3 = new JTextField();
		userTab.add(textField_3, "cell 4 7,grow");
		textField_3.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Login :");
		userTab.add(lblNewLabel_5, "cell 2 9");
		
		textField_4 = new JTextField();
		userTab.add(textField_4, "cell 4 9,grow");
		textField_4.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Mot de passe :");
		userTab.add(lblNewLabel_6, "cell 2 11");
		
		textField_5 = new JTextField();
		userTab.add(textField_5, "cell 4 11,grow");
		textField_5.setColumns(10);
		
		lblNewLabel_7 = new JLabel("E-mail :");
		userTab.add(lblNewLabel_7, "cell 2 13");
		
		textField_6 = new JTextField();
		userTab.add(textField_6, "cell 4 13,grow");
		textField_6.setColumns(10);
		
		lblNewLabel_8 = new JLabel("ID de Classe :");
		userTab.add(lblNewLabel_8, "cell 2 15");
		
		textField_7 = new JTextField();
		userTab.add(textField_7, "cell 4 15,growx");
		textField_7.setColumns(10);
		
		btnNewButton_4 = new JButton("Sauvegarder les modifications");
		userTab.add(btnNewButton_4, "cell 4 16");
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Gestion ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabsPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[69.00][266.00,grow][87.00][78.00,grow][61.00][grow]", "[30.00][]"));
		
		searchLabel = new JLabel("Tapez un nom :");
		panel.add(searchLabel, "cell 0 0");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		tablesLabel = new JLabel("Vous cherchez :");
		panel.add(tablesLabel, "cell 2 0,alignx right");
		
		tablesCombo = new JComboBox();
		tablesCombo.setModel(new DefaultComboBoxModel(new String[] {"Etudiant", "Classe", "Enseignant", "Responsable", "Matiere"}));
		panel.add(tablesCombo, "cell 3 0,growx");
		
		searchBtn = new JButton("Rechercher");
		panel.add(searchBtn, "cell 4 0,growx,aligny center");
		
		consultBtn = new JButton("Consulter");
		panel.add(consultBtn, "flowx,cell 1 1");
		
		deleteBtn = new JButton("Supprimer");
		panel.add(deleteBtn, "cell 1 1");
		
		triLabel = new JLabel("Trier resultats :");
		panel.add(triLabel, "cell 2 1,alignx trailing");
		
		triCombo = new JComboBox();
		triCombo.setModel(new DefaultComboBoxModel(new String[] {"Aucun", "De A a Z", "De Z a A"}));
		panel.add(triCombo, "cell 3 1,growx");
		
		triBtn = new JButton("Trier");
		panel.add(triBtn, "cell 4 1,growx");
		
		addBtn = new JButton("Ajouter");
		panel.add(addBtn, "cell 1 1,growx");


		addBtn.addActionListener(this);
		deleteClassBtn.addActionListener(this);
		editInfoBtn.addActionListener(this);
		consultListBtn.addActionListener(this);
		saveChangeBtn.addActionListener(this);
		btnNewButton_4.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_3.addActionListener(this);
		searchBtn.addActionListener(this);
		triBtn.addActionListener(this);
		consultBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		textField_5.setEditable(false);
		textField_6.setEditable(false);
		textField_7.setEditable(false);
		libelleField.setEditable(false);
		nivField.setEditable(false);
		filiereField.setEditable(false);
		idClassField.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ResultSet myRs;
		if(e.getSource() == searchBtn){
			tabbedPane.setSelectedIndex(0);
			String[][] where = null;
			lastSearch = (String)tablesCombo.getSelectedItem();
			if(textField.getText().length()>0) where = new String[][]{{"id_"+lastSearch , "=", textField.getText()}};
			int nb = DB.count("*" , lastSearch, where);
			myRs = DB.get("*" , lastSearch, where);
			if(lastSearch.equals("Etudiant")){
				String[][] donnees = new String[nb][5];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_etudiant");
						donnees[i][1] =myRs.getString("nom");
						donnees[i][2] =myRs.getString("prenom");
						donnees[i][3] =myRs.getString("id_classe");
						donnees[i][4] =myRs.getString("email");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_etudiant","nom","prenom","id_classe","email"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}
			else if(lastSearch.equals("Classe")){
				String[][] donnees = new String[nb][4];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_classe");
						donnees[i][1] =myRs.getString("libelle");
						donnees[i][2] =myRs.getString("niveau");
						donnees[i][3] =myRs.getString("filiére");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_classe","libelle","niveau","filiére"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}
			else if(lastSearch.equals("Enseignant")){
				String[][] donnees = new String[nb][4];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_enseignant");
						donnees[i][1] =myRs.getString("nom");
						donnees[i][2] =myRs.getString("prenom");
						donnees[i][3] =myRs.getString("email");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_enseignant","nom","prenom"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}
			else if(lastSearch.equals("Responsable")){
				String[][] donnees = new String[nb][4];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_responsable");
						donnees[i][1] =myRs.getString("nom");
						donnees[i][2] =myRs.getString("prenom");
						donnees[i][3] =myRs.getString("email");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_responsable","nom","prenom"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}
			else if(lastSearch.equals("Matiere")){
				String[][] donnees = new String[nb][2];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_matiere");
						donnees[i][1] =myRs.getString("libelle");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_matiere","libelle"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}


		} //fou9
		else if (e.getSource() == consultBtn){
			if(lastSearch.equals("Etudiant")) {
				tabbedPane.setSelectedIndex(2);
				int row = table.getSelectedRow();
				System.out.println(table.getValueAt(row, 0));
				String[][] donnees = {{"id_" + lastSearch, "=", (String) table.getValueAt(row, 0)}};
				myRs = DB.get("*", lastSearch, donnees);
				try {
					myRs.next();
					textField_1.setText(myRs.getString("id_etudiant"));
					textField_2.setText(myRs.getString("nom"));
					textField_3.setText(myRs.getString("prenom"));
					textField_4.setText(myRs.getString("login"));
					textField_5.setText(myRs.getString("pwd"));
					textField_6.setText(myRs.getString("email"));
					textField_7.setText(myRs.getString("id_classe"));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if(lastSearch.equals("Classe")) {
				tabbedPane.setSelectedIndex(1);
				int row = table.getSelectedRow();
				System.out.println(table.getValueAt(row, 0));
				String[][] donnees = {{"id_" + lastSearch, "=", (String) table.getValueAt(row, 0)}};
				myRs = DB.get("*", lastSearch, donnees);
				try {
					myRs.next();
					idClassField.setText(myRs.getString("id_classe"));
					libelleField.setText(myRs.getString("libelle"));
					nivField.setText(myRs.getString("niveau"));
					filiereField.setText(myRs.getString("filiére"));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} //fou9
 		else if (e.getSource() == deleteBtn){
			int row = table.getSelectedRow();
			System.out.println(table.getValueAt(row,0));
			String[][] donnees = {{"id_"+lastSearch , "=", (String)table.getValueAt(row,0)} };
			if(lastSearch == "Etudiant") {
                DB.delete("absence", donnees);
			}
            DB.delete(lastSearch, donnees);
		} //fou9
		else if (e.getSource() == editInfoBtn){
			if(!idClassField.getText().isEmpty()){
			libelleField.setEditable(true);
			nivField.setEditable(true);
			filiereField.setEditable(true);
			}
		} //classe
		else if (e.getSource() == saveChangeBtn){
			if(!idClassField.getText().isEmpty() && !libelleField.getText().isEmpty()  && !nivField.getText().isEmpty() && !filiereField.getText().isEmpty() ){
				String[][] changes = {
						{"libelle",libelleField.getText()},
						{"niveau",nivField.getText()},
						{"filiére",filiereField.getText()}
				};
				String[] where = {"id_classe",idClassField.getText()};
                DB.update("Classe", changes , where);
				libelleField.setEditable(false);
				nivField.setEditable(false);
				filiereField.setEditable(false);
			}
		} //classe
		else if (e.getSource() == deleteClassBtn){
			if(!idClassField.getText().isEmpty()){

				String[][] where = {{"id_classe","=",idClassField.getText()}};
				DB.delete("etudiant", where);

				idClassField.setText("");
				libelleField.setText("");
				nivField.setText("");
				filiereField.setText("");

				libelleField.setEditable(false);
				nivField.setEditable(false);
				filiereField.setEditable(false);
			}
		} //classe
		else if(e.getSource() == consultListBtn){
			tabbedPane.setSelectedIndex(0);
			String[][] where = null;
			lastSearch = "Etudiant";
			 where = new String[][]{{"id_classe" , "=", idClassField.getText()}};
			int nb = DB.count("*" , lastSearch, where);
			myRs = DB.get("*" , lastSearch, where);
			if(lastSearch.equals("Etudiant")){
				String[][] donnees = new String[nb][5];
				for(int i =0;i<nb;i++){
					try{
						myRs.next();
						donnees[i][0] =myRs.getString("id_etudiant");
						donnees[i][1] =myRs.getString("nom");
						donnees[i][2] =myRs.getString("prenom");
						donnees[i][3] =myRs.getString("id_classe");
						donnees[i][4] =myRs.getString("email");
					}
					catch(Exception ex){

					}
				}
				String[] entetes = {"id_etudiant","nom","prenom","id_classe","email"};
				table = new JTable(donnees, entetes);

				ResultsTab.setViewportView(table);
			}
		} // classe
		else if (e.getSource() == btnNewButton_1){
			if(!textField_1.getText().isEmpty()){

				String[][] where = {{"id_etudiant","=",textField_1.getText()}};
                DB.delete("etudiant", where);
				textField_2.setEditable(false);
				textField_3.setEditable(false);
				textField_4.setEditable(false);
				textField_5.setEditable(false);
				textField_6.setEditable(false);
				textField_7.setEditable(false);

				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
			}
		} //person supprimer
		else if (e.getSource() == btnNewButton_4){
			if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty() && !textField_3.getText().isEmpty() &&
					!textField_4.getText().isEmpty() && !textField_5.getText().isEmpty() && !textField_6.getText().isEmpty() && !textField_7.getText().isEmpty()){
				String[][] changes = {

						{"nom",textField_2.getText()},
						{"prenom",textField_3.getText()},
						{"login",textField_4.getText()},
						{"pwd",textField_5.getText()},
						{"email",textField_6.getText()},
						{"id_classe",textField_7.getText()}
				};
				String[] where = {"id_etudiant",textField_1.getText()};
                DB.update("etudiant", changes , where);
				textField_2.setEditable(false);
				textField_3.setEditable(false);
				textField_4.setEditable(false);
				textField_5.setEditable(false);
				textField_6.setEditable(false);
				textField_7.setEditable(false);
			}
		}//person
		else if (e.getSource() == btnNewButton_3){
			if(!textField_1.getText().isEmpty()){
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				textField_4.setEditable(true);
				textField_5.setEditable(true);
				textField_6.setEditable(true);
				textField_7.setEditable(true);
			}
		}//person

		else if(e.getSource() == addBtn){
			new ChooseAdd();
		}
	}
}


class ConsultMatiere extends JPanel{
	private AppFrame frame;
	
	public ConsultMatiere(Matiere matiere)
	{
		frame= new AppFrame("Consulter Matiere",360,300,false);
		frame.getContentPane().add(this, BorderLayout.CENTER);
		this.setLayout(null);
		
		JTextPane libelleText = new JTextPane();
		libelleText.setBounds(62, 89, 219, 102);
		libelleText.setEnabled(false);
		add(libelleText);
		
		JButton editBtn = new JButton("Modifier");
		editBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 11));
		editBtn.setBounds(62, 23, 90, 30);
		add(editBtn);
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				libelleText.setEnabled(true);
			}
		});
		
		JButton deleteBtn = new JButton("Supprimer Matiere");
		deleteBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 11));
		deleteBtn.setBounds(162, 23, 130, 30);
		add(deleteBtn);
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*if(db.delete("*","matiere",new String [][]{{"libelle","=",libelleText.getText()}})!=null) //or ID let me think of that
				{//makePopup-success
				frame.dispose();}*/
			}});
		
		JLabel libelleLabel = new JLabel("Libelle :");
		libelleLabel.setBounds(62, 64, 46, 14);
		add(libelleLabel);
		
		JButton saveBtn = new JButton("Sauvegarder");
		saveBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
		saveBtn.setBounds(117, 212, 112, 33);
		add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DB.update("matiere", new String [][] {{"libelle",libelleText.getText()}}, new String[] {"id_matiere",matiere.getId()}) != 0)
				{
					//popup saved
					frame.dispose();
				}
			}
		});
		
		
	}
}
