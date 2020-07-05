package controller;

import java.awt.Component;
import javax.swing.JOptionPane;
import view.AppFrame;

public class Messages {

    public static void showMessage(int messNum,String title) {
        String txt=null;
        switch (messNum) {
            case 1:
                txt=search; break;
            case 2:
                txt=mail; break;
            case 3:
                txt=stat; break;
            case 4:
                txt=about; break;
        }
        Component pc =null;
        JOptionPane.showMessageDialog(pc,txt,title,JOptionPane.PLAIN_MESSAGE);
    }
    public static int showConfirm(int confNum,String title)
    {
        String txt=null;
        switch (confNum) {
            case 1:
                txt="Voulez vous Vraiment Quitter le programme ?"; break;
            case 2:
                txt="Voulez vous Vraiment Deconnecter de  ce compte ?"; break;
            case 3:
                txt="Sauvegarder les changements ?"; break;
            default:
                txt="voulez vous?";
        }
        Component pc =null;
        return JOptionPane.showConfirmDialog(pc,txt,title,JOptionPane.OK_CANCEL_OPTION);

    }
    public static void showWarning(int warNum) {

        String txt=null;
        switch (warNum) {
            case 1:
                txt="Veuillez remplir les champs"; break;
            case 2:
                txt=""; break;
            case 3:
                txt=""; break;
            default:
                txt="Warning!";
        }
        Component pc =null;
        JOptionPane.showMessageDialog(pc,txt,"Alerte",JOptionPane.WARNING_MESSAGE);
    }

    public static void showError(int errNum)
    {
        String txt=null;
        switch (errNum) {
            case 1:
                txt="Connexion à la base de données a echoué"; break;
            case 2:
                txt="Operation Echouée!"; break;
            case 3:
                txt="Failed to Login"; break;
            case 4:
                txt="Erruer lors de la creation de graphes"; break;
            case 5:
                txt=""; break;
            default:
                txt="Erreur!"; break;
        }

        Component pc =null;
        JOptionPane.showMessageDialog(pc,txt,"Erreur !",JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessage(String txt){
        Component pc =null;
        JOptionPane.showMessageDialog(pc,txt,"Erreur !",JOptionPane.ERROR_MESSAGE);
    }


    //CONTENT
    private static String search =null;
    private static String mail= null;
    private static String stat =null;
    private static String about= null;
}
