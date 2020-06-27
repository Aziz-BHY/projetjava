package view;

import Model.DB;
import Model.Matiere;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultMatiere extends JPanel {
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
