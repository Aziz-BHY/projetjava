package Model;

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
}
