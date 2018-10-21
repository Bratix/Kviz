package com.example.amar.kviz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dodaj_Pitanje extends AppCompatActivity {

    EditText tekst_pitanja,prvi_odg,drugi_odg,treci_odg,tacan_odg;
    DatabaseReference dbpitanja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj__pitanje);

        dbpitanja = FirebaseDatabase.getInstance().getReference("pitanje");
        tekst_pitanja = findViewById(R.id.tekst_pitanja);
        prvi_odg = findViewById(R.id.prvi_odg);
        drugi_odg = findViewById(R.id.drugi_odg);
        treci_odg = findViewById(R.id.treci_odg);
        tacan_odg = findViewById(R.id.tacan_odg);

    }


    public void kreiraj_pitanje(View v){

        String tp = tekst_pitanja.getText().toString().trim();
        String po = prvi_odg.getText().toString().trim();
        String dro = drugi_odg.getText().toString().trim();
        String tro = treci_odg.getText().toString().trim();
        String tao = tacan_odg.getText().toString().trim();

        String id = dbpitanja.push().getKey();

        Pitanje p = new Pitanje(id,tp,po,dro,tro,tao);

        if(!TextUtils.isEmpty(tp) && !TextUtils.isEmpty(po) && !TextUtils.isEmpty(dro) && !TextUtils.isEmpty(tro) && !TextUtils.isEmpty(tao)) {
            dbpitanja.child(id).setValue(p);
            tekst_pitanja.setText("");
            prvi_odg.setText("");
            drugi_odg.setText("");
            treci_odg.setText("");
            tacan_odg.setText("");
            Toast.makeText(this,"Pitanje dodano",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Sva polja nisu unešena",Toast.LENGTH_SHORT).show();
        }

    }
}
