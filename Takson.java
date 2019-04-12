package df;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Takson {
    private int id;
    private String rodzaj;
    private String gatunek;
    private int n2;
    private int x;
    private String uwagi;

    public Takson(int id, String rodzaj, String gatunek, int n2, int x) {
        this.id = id;
        this.rodzaj = rodzaj;
        this.gatunek = gatunek;
        this.n2 = n2;
        this.x = x;
    }

    public int getId() {
        return id;
    }

    public void setId(int nr) {
        this.id = id;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }



}