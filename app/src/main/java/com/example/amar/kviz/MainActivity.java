package com.example.amar.kviz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void igraj(View v){
        i = new Intent(this, Kviz.class);
        startActivity(i);
    }

    public void izlistaj_najbolje(View v){
        i = new Intent(this, Lista_Igraca.class);
        startActivity(i);
    }

    public void dodaj_pitanje(View v){
        i = new Intent(this,Dodaj_Pitanje.class);
        startActivity(i);
    }

    public void izlaz(View v){
        finish();
        System.exit(0);
    }



}
