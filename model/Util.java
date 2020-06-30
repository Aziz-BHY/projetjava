package Model;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {


    public static String[][] concat(String[][] donnees1 , String[][] donnees2 , int x){
        int length1 = donnees1.length;
        int length2 = donnees2.length;
        int length = length1 + length2;
        String[][] donnes = new String[length][x];
        for(int i =0; i<length1 ; i++){
            for(int j = 0 ; j<x ; j++){
                donnes[i][j] = donnees1[i][j];
            }
        }
        for(int i =0; i<length2 ; i++){
            for(int j = 0 ; j<x ; j++){
                donnes[i+length1][j] = donnees2[i][j];
            }
        }
        return donnes;
    }




    public static void imprimer() throws IOException, SQLException {
        ResultSet myRs = DB.get("SELECT numseance , date_abs , etudiant.nom nom_etudiant , enseignant.nom nom_enseignant, matiere.nom_matiere\n" +
                        " FROM absence , etudiant , enseignant , matiere \n" +
                        " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                        " and absence.id_matiere = matiere.id_matiere;");
        ResultSet count = DB.get("SELECT count(*)\n" +
                " FROM absence , etudiant , enseignant , matiere \n" +
                " where absence.id_etudiant = etudiant.id_etudiant and absence.id_enseignant = enseignant.id_enseignant \n" +
                " and absence.id_matiere = matiere.id_matiere ;");
        count.next();
        int x = Integer.parseInt(count.getString("count(*)"));
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

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("firsttry");
        int rowMax = donnees.length;
        int cellMax = entetes.length;
        Cell cell;
        Row row;
        int rownum = 0;

        row = sheet.createRow(rownum);
        for(int j = 0; j<cellMax ; j++){
            cell = row.createCell(j, CellType.STRING);
            cell.setCellValue(entetes[j]);
        }


        while(rownum<rowMax){
            rownum++;
            row = sheet.createRow(rownum);
            for(int j = 0; j<cellMax ; j++){
                cell = row.createCell(j, CellType.STRING);
                cell.setCellValue(donnees[rownum-1][j]);
            }
        }

        File file = new File("C:\\Users\\aziz ben hadj yahia\\Desktop\\projetjava\\firsttry.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        sheet.getWorkbook().write(outFile);
    }

    public static void envoyer(String titre, String contenu, String destinataire) {
// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication( "azizbenhadjyahia99@gmail.com", "chiheb147bebe");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("azizbenhadjyahia99@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinataire));
            message.setSubject(titre);
            message.setText(contenu);
            Transport.send(message);
            System.out.println("Message_envoye");
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
