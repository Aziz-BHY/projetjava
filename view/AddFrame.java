package view;

import Model.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JPanel  implements ActionListener
{

    private AppFrame frame;
    private JButton addBtn;
    //addUser Components
    private JTextField nivField;
    private JTextField filiereField;
    private JTextField prenField;
    private JTextField nameField;
    private JTextField loginField;
    private JTextField mailField;
    private JPasswordField passwordField;
    private JTextField idField;
    private JTextPane textPane;
    //addMatiere
    private JLabel libelleLabel;
    private JTextPane libelleText;

    //addClasse
    private JLabel nivLabel;
    private JLabel filiereLabel;
    private JLabel libellecLabel;

    private String categ;

    public AddFrame(String categ) {

        this.categ = categ;
        frame = new AppFrame("Ajouter " + categ, 360, 360, false);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        this.setLayout(null);

        addBtn = new JButton("Ajouter");
        addBtn.setFont(new Font("Titillium Web SemiBold", Font.BOLD, 12));
        addBtn.setBounds(120, 250, 110, 35);
        addBtn.addActionListener(this);
        add(addBtn);
        if (categ == "Matiere") {
            showMatiere();
            addBtn.setBounds(125, 200, 110, 35);
        } else if (categ == "Classe") {
            showClasse();
        } else if (categ == "Etudiant" || categ == "Responsable" || categ == "Enseignant") {
            showUser(categ);
        }

    }
    /*

     */








    /*ADD

     */


    public void showUser(String categ) {
        Font fnt = new Font("Titillium Web SemiBold", Font.PLAIN, 12);
        JLabel prenLabel = new JLabel("Prenom :");
        prenLabel.setFont(fnt);
        prenLabel.setBounds(71, 47, 59, 14);
        add(prenLabel);

        JLabel nameLabel = new JLabel("Nom :");
        nameLabel.setFont(fnt);
        nameLabel.setBounds(71, 78, 59, 14);
        add(nameLabel);

        JLabel loginLabel = new JLabel("Login :");
        loginLabel.setFont(fnt);
        loginLabel.setBounds(71, 111, 59, 14);
        add(loginLabel);

        JLabel passLabel = new JLabel("Password :");
        passLabel.setFont(fnt);
        passLabel.setBounds(71, 142, 59, 14);
        add(passLabel);

        JLabel mailLabel = new JLabel("E-mail :");
        mailLabel.setFont(fnt);
        mailLabel.setBounds(71, 176, 59, 14);
        add(mailLabel);

        prenField = new JTextField();
        prenField.setBounds(140, 44, 143, 20);
        add(prenField);
        prenField.setColumns(25);

        nameField = new JTextField();
        nameField.setBounds(140, 75, 143, 20);
        add(nameField);
        nameField.setColumns(25);

        loginField = new JTextField();
        loginField.setBounds(140, 108, 143, 20);
        add(loginField);
        loginField.setColumns(15);

        mailField = new JTextField();
        mailField.setBounds(140, 170, 143, 20);
        add(mailField);
        mailField.setColumns(64);

        passwordField = new JPasswordField();
        passwordField.setColumns(25);
        passwordField.setBounds(140, 139, 143, 20);
        add(passwordField);

        if (categ == "Etudiant") {
            JLabel idLabel = new JLabel("ID Classe :");
            idLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 12));
            idLabel.setBounds(71, 204, 59, 14);
            add(idLabel);

            idField = new JTextField();
            idField.setBounds(140, 201, 143, 20);
            add(idField);
            idField.setColumns(10);
        }

    }

    public void showClasse() {

        Font fnt = new Font("Titillium Web SemiBold", Font.PLAIN, 12);
        nivLabel = new JLabel("Niveau :");
        nivLabel.setFont(fnt);
        nivLabel.setBounds(71, 38, 70, 14);
        add(nivLabel);

        filiereLabel = new JLabel("Filiere :");
        filiereLabel.setFont(fnt);
        filiereLabel.setBounds(71, 63, 70, 14);
        add(filiereLabel);

        libellecLabel = new JLabel("Libelle :");
        libellecLabel.setFont(fnt);
        libellecLabel.setBounds(71, 88, 70, 14);
        add(libellecLabel);

        nivField = new JTextField();
        nivField.setBounds(151, 35, 132, 20);
        add(nivField);
        nivField.setColumns(10);

        filiereField = new JTextField();
        filiereField.setBounds(151, 60, 132, 20);
        add(filiereField);
        filiereField.setColumns(10);

        textPane = new JTextPane();
        textPane.setBounds(71, 113, 212, 117);
        add(textPane);


    }

    public void showMatiere() {
        frame.setSize(360, 300);
        libelleLabel = new JLabel("Libelle :");
        libelleLabel.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 12));
        libelleLabel.setBounds(70, 30, 92, 22);
        add(libelleLabel);

        libelleText = new JTextPane();
        libelleText.setBounds(70, 60, 200, 125);
        add(libelleText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            if (categ == "Etudiant") {
                if (!prenField.getText().isEmpty() && !nameField.getText().isEmpty() && !loginField.getText().isEmpty() &&
                        !mailField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty() && !idField.getText().isEmpty()) {
                    String[][] donnees = {
                            {"id_etudiant", "0"},
                            {"nom", prenField.getText()},
                            {"prenom", nameField.getText()},
                            {"login", loginField.getText()},
                            {"pwd", String.valueOf(passwordField.getPassword()) },
                            {"email", mailField.getText()},
                            {"id_classe", idField.getText()}
                    };
                    DB.insert("etudiant", donnees);
                    frame.dispose();
                }
            }
            else if (categ == "Classe") {
                if (!nivField.getText().isEmpty() && !filiereField.getText().isEmpty() && !textPane.getText().isEmpty()) {
                    String[][] donnees = {
                            {"id_classe", "0"},
                            {"libelle", textPane.getText()},
                            {"niveau", nivField.getText()},
                            {"filiére", filiereField.getText()}
                    };
                    DB.insert("classe", donnees);
                    frame.dispose();
                }
            }
            else if (categ == "Enseignant") {
                if (!prenField.getText().isEmpty() && !nameField.getText().isEmpty() && !loginField.getText().isEmpty() &&
                        !mailField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty()) {
                    String[][] donnees = {
                            {"id_enseignant", "0"},
                            {"nom", prenField.getText()},
                            {"prenom", nameField.getText()},
                            {"login", loginField.getText()},
                            {"pwd", String.valueOf(passwordField.getPassword()) },
                            {"email", mailField.getText()}
                    };
                    DB.insert("enseignant", donnees);
                    frame.dispose();
                }
            }
            else if (categ == "Matiere") {
                if (!libelleText.getText().isEmpty()) {
                    String[][] donnees = {
                            {"id_matiere", "0"},
                            {"libelle", libelleText.getText()}

                    };
                    DB.insert("matiere", donnees);
                    frame.dispose();
                }
            }
            else if (categ == "Responsable") {
                if (!prenField.getText().isEmpty() && !nameField.getText().isEmpty() && !loginField.getText().isEmpty() &&
                        !mailField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty()) {
                    String[][] donnees = {
                            {"id_responsable", "0"},
                            {"nom", prenField.getText()},
                            {"prenom", nameField.getText()},
                            {"login", loginField.getText()},
                            {"pwd", String.valueOf(passwordField.getPassword()) },
                            {"email", mailField.getText()}
                    };
                    DB.insert("responsable", donnees);
                    frame.dispose();
                }
            }
        }
    }
}
