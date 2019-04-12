package df;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataFrameDB extends DataFrame { //wczytywanie i zapis do bazy danych, DataFrame() musi moc zwrocic niezalezny od bazy obiekt DF

    public static void main(String[] args){

    }

    DataFrameDB(DataFrame df){
        String baza = "nowaBazwa9"; //jednoczesnie nazwa tabeli, trzeba zmieniac za kazdym razem
        Connection polaczenie = polacz(baza);
      //  String nazwaTabeli = "tabela1";
        stworzTabele(polaczenie, baza);

        dodajDane(df, baza);

       /* int rozmiarDF = df.size();

        for(int i=0; i<rozmiarDF; i++){
            DataFrame rzad = df.iloc(i);
            dodajDane(rzad, baza);
        }*/


        //zrobic get row z mojej df
     /*   Takson gatunek1 = new Takson(1, "Acer", "negundo", 26, 13);
        dodajDane(gatunek1, baza);
        Takson gatunek2 = new Takson(2, "Acer", "saccharinum", 52, 13);
        dodajDane(gatunek2, baza);
        Takson gatunek3 = new Takson(3, "Amaranthus", "bouchonii", 52, 13);
        gatunek3.setUwagi("Sprawdzić dane!");
        dodajDane(gatunek3, baza);

*/

    }


    public static Connection polacz(String baza) { //jesli bazy nie ma to zostanie utworzona
        Connection polaczenie = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Wskazanie jaki rodzaj bazy danych będzie wykorzystany, tu sqlite

            polaczenie = DriverManager.getConnection("jdbc:sqlite:"+baza+".db");// Połączenie, wskazujemy rodzaj bazy i jej nazwę
            System.out.println("Połączyłem się z bazą "+baza);
        } catch (Exception e) {
            System.err.println("Błąd w połączeniu z bazą: \n" + e.getMessage());
            return null;
        }
        return polaczenie;
    }


    public static void stworzTabele(Connection polaczenie, String tabela) {//chyba tworzymy tylko 1 tabele
        // Obiekt odpowiadający za wykonanie instrukcji   , poki co testuje dla groupby.csv
        Statement stat = null;
        try {
            stat = polaczenie.createStatement();
            // polecenie SQL tworzące tablicę



            String tabelaSQL = "CREATE TABLE " + tabela
                    + " (NR_RZEDU INT PRIMARY KEY     NOT NULL,"     //jaki ja mam zrobic primary key? nr rzedu? tak zrobie
                    + " ID          TEXT    NOT NULL, "
                    + " DATE_        DATE     NOT NULL, "
                    + " TOTAL             REAL, "
                    + " VAL              REAL) ";
                   // + " UWAGI          TEXT)"; //ZOSTAWIE ZEBY POTEM WIEDZIEC O CO CHODZI
            // wywołanie polecenia
            stat.executeUpdate(tabelaSQL);
            // zamykanie wywołania i połączenia
            stat.close();
            polaczenie.close();
            System.out.println("stworzylam tabele");
        } catch (SQLException e) {
            System.out.println("Nie mogę stworzyć tabeli" + e.getMessage());
        }
    }

    public static void dodajDane(DataFrame df, String baza) {
        Connection polaczenie = null;
        Statement stat = null;
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:" + baza + ".db");

            stat = polaczenie.createStatement();


            int rozmiarDF = df.size();

            ArrayList<Integer> kolumnyNumeryczne = new ArrayList<>();
            for(int y=0; y<df.vTypes.length; y++){
                if(df.vTypes[y].toString().equals("class df.IntegerValue") || df.vTypes[y].toString().equals("class df.DoubleValue") || df.vTypes[y].toString().equals("class df.FloatValue")){
                    kolumnyNumeryczne.add(y);
                }
            }

            for(int i=0; i<rozmiarDF; i++) {
                ArrayList<Value> rzad = df.getRow(i);
                /*for(int t=0; t<rzad.size(); t++){
                    System.out.println(rzad.get(t));
                }*/
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT INTO " + baza + " (NR_RZEDU, ID, DATE_, TOTAL, VAL) VALUES ("); //te nazwy tez tak nie hardcodowac


                for (int j = 0; j < rzad.size(); j++) {
                    if(j==0){
                        sb.append(i + ",");  //primary key, czyli jakies id u mnie zeby bylo
                    }
                    if(j!=0){
                        sb.append(", ");
                    }
                    if (kolumnyNumeryczne.contains(j)) {
                        sb.append(rzad.get(j));
                    } else {
                        sb.append("'" + rzad.get(j).toString() + "'");
                    }
                }
                sb.append(");");
                //System.out.println(sb);

                String dodajSQL = sb.toString();
                stat.executeUpdate(dodajSQL);
                //System.out.println("Polecenie: \n" + dodajSQL + "\n wykonane.");

            }

            System.out.println("Wszystkie rekordy zostaly dodane o bazy");


            // "INSERT INTO " + baza + " (NR_RZEDU, ID, DATE_, TOTAL, VAL) " //czyli tytuly
               /*     + "VALUES ("
                    + takson.mainFrame.get(1) + ","
                    + "'" + takson.mainFrame.get(1) + "',"
                    + "'" + takson.mainFrame.get(1) + "',"
                    + takson.mainFrame.get(1) + ","
                    + takson.mainFrame.get(1) + ","   */
                    //+ "'" + takson.getUwagi() + "'" - potencjalnie przydatne na pozniej
                  //  + "  );";

            stat.close();
            polaczenie.close();
            // Komunikat i wydrukowanie końcowej formy polecenia SQL

        } catch (Exception e) {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }







}
