package Model;

import java.sql.*;
import java.util.Arrays;

public abstract class DB {
    private static String operators[] = {"=",">","<",">=","<=","<>"};

    public static Statement connect()
    {
        String dbUsername = "root";
        String dbPass = "MyNewPass";
        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",dbUsername,dbPass);
            Statement myStmt = myConn.createStatement();
            return myStmt;
        }
        catch(Exception e){
            return null;
        }
    }




    public static ResultSet get (String what, String table, String[][] wheres) {
        String where="";
        Statement myStmt = DB.connect();
        if(wheres==null) {
            try {
                return(myStmt.executeQuery("SELECT"+what+" FROM "+table+";"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(wheres.length>0)
        {
            for(int i=0;i<wheres.length;i++) {
                if(wheres[i].length==3 && Arrays.asList(operators).contains(wheres[i][1]))
                {
                    if (!wheres[i][2].matches("[0-9]+")) wheres[i][2]="'"+wheres[i][2]+"'";
                    if(i==0) {where=wheres[0][0]+wheres[0][1]+wheres[0][2];}
                    else{where+=" AND "+wheres[i][0]+wheres[i][1]+wheres[i][2];}
                }
            }
            try {

                return (myStmt.executeQuery("SELECT "+what+" FROM "+table+" WHERE "+where+";"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return null;

    }

    public static int delete (String table, String[][] wheres){
        String where="";
        Statement myStmt = DB.connect();

        if(wheres.length>0)
        {
            for(int i=0;i<wheres.length;i++) {
                if(wheres[i].length==3 && Arrays.asList(operators).contains(wheres[i][1]))
                {
                    if (!wheres[i][2].matches("[0-9]+")) wheres[i][2]="'"+wheres[i][2]+"'";
                    if(i==0) {where=wheres[0][0]+wheres[0][1]+wheres[0][2];}
                    else{where.concat(" AND "+wheres[i][0]+wheres[i][1]+wheres[i][2]);}
                }
            }
            try {
                System.out.println("delete from "+table + " where " + where +" ;");
                return(myStmt.executeUpdate(new String ("delete from "+table + " where " + where +" ;")));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        return 0;
    }



    public static void insert (String table, String[][]fields)
    {
        String cols ="(" ;
        String vals= "(";
        Statement myStmt = DB.connect();
        for(int i=0;i<fields.length;i++)
        {
            if(fields[i].length!=2 ){break;}

            cols += (i == fields.length - 1) ? fields[i][0] + ")" : fields[i][0]+" , ";
            if(fields[i][1].matches("[0-9]+"))
            {
                vals+= ((i == fields.length - 1) ? fields[i][1] + " )" : fields[i][1] + " , " );
            }
            else
            {
                vals+= "'"+ ((i == fields.length - 1) ? fields[i][1] + "')" : fields[i][1] + "' , " );
            }

        }
        try {
            System.out.println("INSERT INTO " +table+" "+cols+" VALUES "+vals);
            myStmt.executeUpdate(new String("INSERT INTO "+table+" "+cols+" VALUES "+vals));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            /*ADD DIALOGUE WINDOW HERE with the dialogue class*/
        }
    }


    public static  int update(String table, String[][] fields,String[]where)
    {	String changes="";
        Statement myStmt = DB.connect();

        for(int i=0;i<fields.length;i++) {
            if(fields[i].length!=2 ){break;}

            if(fields[i][1].matches("[0-9]+"))
            {
                changes+= fields[i][0]+" = "+fields[i][1];
                if(i != fields.length - 1)  changes+= " , " ;
            }
            else
            {
                changes+= fields[i][0]+" = '"+fields[i][1]+((i == fields.length - 1) ?   "'" :  "' , " );
            }
        }

        try {
            return myStmt.executeUpdate("UPDATE "+table+" SET "+changes+" WHERE "+where[0]+"= '"+where[1]+"' ;");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            /*ADD DIALOGUE WINDOW HERE with the dialogue class*/
        }
        return 0;
    }

    public static int  count (String what, String table, String[][] wheres) {
        String where="";
        ResultSet myRs = null;
        Statement myStmt = DB.connect();

        if(wheres==null) {
            try {
                myRs = myStmt.executeQuery("SELECT count("+what+") FROM "+table+";");
                myRs.next();
                return( Integer.parseInt(myRs.getString("count("+what+")")));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(wheres.length>0)
        {
            for(int i=0;i<wheres.length;i++) {
                if(wheres[i].length==3 && Arrays.asList(operators).contains(wheres[i][1]))
                {
                    if (!wheres[i][2].matches("[0-9]+")) wheres[i][2]+="'"+wheres[i][2]+"'";
                    if(i==0) {where=wheres[0][0]+wheres[0][1]+wheres[0][2];}
                    else{where.concat(" AND "+wheres[i][0]+wheres[i][1]+wheres[i][2]);}
                }
            }
            try {
                myRs = myStmt.executeQuery ("SELECT count("+what+") FROM "+table+" WHERE "+where+";");
                myRs.next();
                return( Integer.parseInt(myRs.getString("count("+what+")")));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return 0;

    }



}
