package view;

import Model.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AppFrame extends JFrame implements ActionListener{
    private ImageIcon icon;
    private String nom;
    private JMenuItem printItem;
    private String[][] donnees;
    private String[] entete;



    /**
     * @wbp.parser.constructor
     */
    public AppFrame(String title,int width,int height, boolean resize)
    {
        super(title);

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(resize);//make the window unresizeable
        if(resize) {setJMenuBar(createMenuBar());}
        this.setVisible(true);

    }


	/*
	//Constructeur pour les Erreurs

	public AppFrame(String title,String src)
	{
		super(title);
		icon = new ImageIcon(src);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(icon.getImage());
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	*/


    public void init_fichier(String nom , String[][] donnees , String[] entete){
        this.nom = nom;
        this.donnees = donnees;
        this.entete = entete;
    }

    private JMenuBar createMenuBar() {
        // Creating the Bar
        JMenuBar menuBar= new JMenuBar();
        //Creating the file menu
        JMenu controlMenu= new JMenu("Control");
        JMenu windowMenu= new JMenu("Window");
        JMenu helpMenu = new JMenu("Help");

        //Creating menu Items
        /*file*/
         printItem= new JMenuItem("Imprimer");
        printItem.addActionListener(this);
        JMenuItem exitItem= new JMenuItem("Exit");

        /*window*/
        JMenuItem prefsItem= new JMenuItem("Preferences...") ;
        JMenu showMenu= new JMenu("Show");
        JCheckBoxMenuItem showFormItem= new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);

        /*Help*/
        JMenu functionsMenu = new JMenu("Fonctionnalites du programme");
        JMenuItem  i1= new JMenuItem("Comment faire...");
        JMenuItem  i2= new JMenuItem("Comment faire...");
        JMenuItem  i3= new JMenuItem("Comment faire...");
        JMenuItem aboutItem =new JMenuItem("A propos l\'application");

        functionsMenu.add(i1);
        functionsMenu.add(i2);
        functionsMenu.add(i3);


        // Adding menus to the Bar
        menuBar.add(controlMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);
        //Adding Items to the menu
        /*File*/
        controlMenu.add(printItem);
        controlMenu.addSeparator();
        controlMenu.add(exitItem);
        /*Window*/
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        /*Help*/
        helpMenu.add(functionsMenu);
        helpMenu.add(aboutItem);


		/*prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}

		});
		//enable-disable something
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem=(JCheckBoxMenuItem)ev.getSource();
				formPanel.setVisible(menuItem.isSelected());

			}});

		*/

        //Setting Mnemonics (shortcuts)
        controlMenu.setMnemonic(KeyEvent.VK_C);
        windowMenu.setMnemonic(KeyEvent.VK_W);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));


		/*importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
				{
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,"Could not load data from File", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});*/

		/*exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this)== JFileChooser.APPROVE_OPTION)
				{
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,"Could not save data to File", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});*/

        //Confirming exit
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action= JOptionPane.showConfirmDialog(AppFrame.this,"Do you really want to exit the prgram?","Conirm Exit",JOptionPane.OK_CANCEL_OPTION);
                if(action== JOptionPane.OK_OPTION)
                {System.exit(0);}
            }
        });

        //Inputting information:
        //String text=JOptionPane.showInputDialog(MainFrame.this,"Enter the Username","Username Input",JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);



        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == printItem){
            try {
                Util.imprimer(nom , donnees, entete);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
