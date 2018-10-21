package com.example.amar.kviz;

public class Pitanje {
    public String pitanje,odgovor1,odgovor2,odgovor3,tacan_odgovor,id;


    public Pitanje(String id, String pitanje, String odgovor1, String odgovor2 ,String odgovor3, String tacan_odgovor) {
        this.pitanje = pitanje;
        this.odgovor1 = odgovor1;
        this.odgovor2 = odgovor2;
        this.odgovor3 = odgovor3;
        this.tacan_odgovor = tacan_odgovor;
        this.id = id;
    }

    public Pitanje() {
    }


    public boolean provjera_odgovora(String odgovor){
        if(odgovor.equals(tacan_odgovor))
            return true;
        else
            return false;
    }
}