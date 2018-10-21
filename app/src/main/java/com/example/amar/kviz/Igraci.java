package com.example.amar.kviz;

public class Igraci {
    String id;
    int broj_bodova;
    String ime;


    public Igraci() {
    }

    public Igraci(int broj_bodova, String ime, String id) {
        this.broj_bodova = broj_bodova;
        this.ime = ime;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getBroj_bodova() {
        return broj_bodova;
    }

    public String getIme() {
        return ime;
    }


    public String toString(int i) {
        return  Integer.toString(i) + "." +
                " Ime: " + ime +  "    " + "\n" +
                "Broj osvojenih bodova: " + Integer.toString(broj_bodova);
    }
}
