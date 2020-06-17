package view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import model.DB;
import model.User;

public class ChangePass extends JPanel{
	
		private AppFrame frame;
		private JPasswordField oldPassField;
		private JPasswordField newPassField;
		private JPasswordField newrePassField;
		
		private JButton confirmBtn;
		
		private Font fontLabel = new Font("Titillium Web", Font.PLAIN, 13);
	
		public ChangePass(User user) {
			
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
}
