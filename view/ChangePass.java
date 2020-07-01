package view;

import Model.DB;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ChangePass extends JPanel implements ActionListener {
    private AppFrame frame;
    private JPasswordField oldPassField;
    private JPasswordField newPassField;
    private JPasswordField newrePassField;
    private User user;
    private JButton confirmBtn;

    private Font fontLabel = new Font("Titillium Web", Font.PLAIN, 13);

    public ChangePass(User user) {
        this.user = user;
        frame = new AppFrame("Changer Mot de pass",250,300,false);

        frame.getContentPane().add(this, BorderLayout.CENTER);
        this.setLayout(null);

        JLabel oldPasslabel = new JLabel("Ancien mot de passe:");
        oldPasslabel.setFont(fontLabel);
        oldPasslabel.setBounds(50, 15, 150, 25);
        add(oldPasslabel);

        JLabel newPasslabel = new JLabel("Nouveau mot de passe :");
        newPasslabel.setFont(fontLabel);
        newPasslabel.setBounds(50, 75, 150, 25);
        add(newPasslabel);

        JLabel newrePassLabel = new JLabel("Retaper le mot de passe :");
        newrePassLabel.setFont(fontLabel);
        newrePassLabel.setBounds(50, 135, 150, 25);
        add(newrePassLabel);

        confirmBtn = new JButton("Confirmer Changement");
        confirmBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 13));
        confirmBtn.setBounds(40, 205, 170, 35);
        confirmBtn.addActionListener(this);
        add(confirmBtn);

        oldPassField = new JPasswordField();
        oldPassField.setColumns(25);
        oldPassField.setBounds(50, 45, 150, 25);
        add(oldPassField);

        newPassField = new JPasswordField();
        newPassField.setColumns(25);
        newPassField.setBounds(50, 105, 150, 25);
        add(newPassField);

        newrePassField = new JPasswordField();
        newrePassField.setColumns(25);
        newrePassField.setBounds(50, 165, 150, 25);
        add(newrePassField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn ){
            String ancien = String.valueOf(oldPassField.getPassword());
            String new1   = String.valueOf(newPassField.getPassword());
            String new2   = String.valueOf(newrePassField.getPassword());

            if(ancien.isEmpty() || new1.isEmpty() || new2.isEmpty()){
               //erreur
                System.out.println("erreur1");
            }
            else {
                if(!new1.equals(new2)){
                    System.out.println("erreur2");
                }else
                {
                    try {
                        ResultSet rs = DB.get("pwd", user.categ, new String[][]{
                                {"id_" + user.categ, "=", user.id + ""}
                        });
                        rs.next();
                        if(ancien.equals(rs.getString("pwd"))){
                            System.out.println("c bon");
                            System.out.println(new1 +" " + new2 + " " + ancien);
                            DB.update(user.categ , new String[][]{
                                            {"pwd"  , new1}
                            } ,
                                new String[]     {"id_" + user.categ, user.id + ""});
                            frame.dispose();
                        }
                    }
                    catch(Exception ex){
                        System.out.println("erreur3");

                    }
                }
            }
        }
    }
}
