package com.example.amar.kviz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Kviz extends AppCompatActivity {

    RadioGroup radio_group;
    RadioButton radiobutton;
    ArrayList<Pitanje> listapitanja;
    DatabaseReference dbpitanja;


    public void askquestion(int i){
        TextView tekst_pitanja = findViewById(R.id.tekst_pitanja);
        tekst_pitanja.setText(listapitanja.get(i).pitanje);
        RadioButton radio1 = findViewById(R.id.radioButton1);
        radio1.setText(listapitanja.get(i).odgovor1);
        RadioButton radio2 = findViewById(R.id.radioButton2);
        radio2.setText(listapitanja.get(i).odgovor2);
        RadioButton radio3 = findViewById(R.id.radioButton3);
        radio3.setText(listapitanja.get(i).odgovor3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);
        listapitanja = new ArrayList <Pitanje>();
        dbpitanja = FirebaseDatabase.getInstance().getReference("pitanje");


        dbpitanja.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listapitanja.clear();

                for (DataSnapshot pitanjeSnap: dataSnapshot.getChildren()){
                    Pitanje p = pitanjeSnap.getValue(Pitanje.class);
                    listapitanja.add(p);
                }

                Collections.shuffle(listapitanja);

                askquestion(0);
                Button ok_button = findViewById(R.id.kviz_ok_button);
                ok_button.setOnClickListener(new View.OnClickListener(){
                    int j = 0;
                    int br = 0;
                    Intent in = new Intent(Kviz.this, Bodovi.class);
                    @Override
                    public void onClick(View v) {


                        radio_group = findViewById(R.id.radio_group);
                        int radioid = radio_group.getCheckedRadioButtonId();

                        if (radioid != -1){
                            radiobutton = findViewById(radioid);

                            if (listapitanja.get(j).provjera_odgovora(radiobutton.getText().toString()) == true)
                                br++;

                            j++;

                            if (j == 10) {
                                in.putExtra("bodovi",Integer.toString(br));
                                radio_group.clearCheck();
                                startActivity(in);
                                finish();
                                return;
                            }
                                askquestion(j);
                                radio_group.clearCheck();

                        }else{
                            Toast.makeText(Kviz.this,"Selektirajte odgovor",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
