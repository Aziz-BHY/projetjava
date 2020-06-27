package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChooseAdd extends JPanel {
    private AppFrame frame;
    public ChooseAdd(){
        frame= new AppFrame("Ajouter",360,130,false);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        this.setLayout(null);

        JLabel choiceLabel = new JLabel("Je veux Ajouter :");
        choiceLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        choiceLabel.setBounds(69, 11, 92, 22);
        add(choiceLabel);

        JComboBox choiceCombo = new JComboBox();
        choiceCombo.setFont(new Font("Titillium Web", Font.PLAIN, 12));
        choiceCombo.setModel(new DefaultComboBoxModel(new String[] {"Etudiant","Classe", "Enseignant", "Matiere", "Responsable"}));
        choiceCombo.setBounds(69, 38, 128, 22);
        add(choiceCombo);

        JButton okBtn = new JButton("Choix");
        okBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        okBtn.setBounds(214, 36, 69, 25);
        add(okBtn);



        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ch=(String)choiceCombo.getSelectedItem();
                new AddFrame(ch);
                frame.dispose();
            }});
    }
}
