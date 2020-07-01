package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String categ;
    public String name;
    public int id;
    public int classe_id;

    private String login;
    private String pwd;


    public User(ResultSet rs, String userCat) throws SQLException {
        categ = userCat;
        name = rs.getString("nom") + " " + rs.getString("prenom");

        login = rs.getString("login");
        pwd = rs.getString("pwd");

        id =Integer.parseInt( rs.getString("id_"+userCat));
        if(categ.equals("etudiant"))
            classe_id= rs.getInt("id_classe");

    }
    public String getClasse() {
        System.out.println("inside");
        ResultSet rs =DB.get("*", "classe", new String[][] {{"id_classe","=",String.valueOf(classe_id)}});
        try {
            if(rs.next())
            {
                System.out.println(rs.getString("filiére"));
                return rs.getString("filiére")+String.valueOf(rs.getInt("niveau"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
