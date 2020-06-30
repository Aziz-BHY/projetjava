package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String categ;
    public String name;
    public int id;

    private String login;
    private String pwd;


    public User(ResultSet rs, String userCat) throws SQLException {
        categ = userCat;
        name = rs.getString("nom") + " " + rs.getString("prenom");

        login = rs.getString("login");
        pwd = rs.getString("pwd");

        id =Integer.parseInt( rs.getString("id_"+userCat));


    }
    public String getClasse() {
        ResultSet rs =DB.get("*", "classe", new String[][] {{"id_classe","=",String.valueOf(id)}});
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
