package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String categ;
    public String name;
    public int classe_id;

    private String login;
    private String pwd;


    public User(ResultSet rs, String userCat) throws SQLException {
        categ= userCat;
        name= rs.getString(2)+" "+rs.getString(3);

        login= rs.getString(4);
        pwd= rs.getString(5);

        if(categ=="etudiant")
            classe_id= rs.getInt(6);

    }

    public String getClasse() {
        ResultSet rs =DB.get("*", "classe", new String[][] {{"id_classe","=",String.valueOf(classe_id)}});
        try {
            if(rs.next())
            {
                return rs.getString(4)+String.valueOf(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
