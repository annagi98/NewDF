package df;

import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    public void connect(){
        try {
            //
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/annanagi",
                            "annanagi","XjNXvR51f6KiXAg0");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }

    public void listNames(){
        try {
            connect();
            stmt = conn.createStatement();


            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users

        //    System.out.println("przed rs");
            rs = stmt.executeQuery("SELECT title FROM books"); //tutaj sobie zmieniam polecenia


            while(rs.next()){
                String name = rs.getString(1);
                System.out.println("Uzytkownik: "+name);
            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }



}