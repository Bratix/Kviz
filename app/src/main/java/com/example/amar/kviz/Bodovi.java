package com.example.amar.kviz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bodovi extends AppCompatActivity {

    DatabaseReference dbigraci;
    EditText ime_edit;
    String value;
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodovi);

        Bundle extras = getIntent().getExtras();

        value = extras.getString("bodovi");

        TextView text = findViewById(R.id.osvojeni_bodovi);
        text.setText(value);

        dbigraci = FirebaseDatabase.getInstance().getReference("igraci");
        ime_edit = findViewById(R.id.ime_bodovi);


    }


    public void dodaj_igraca_u_bazu(View v){
        String id = dbigraci.push().getKey();
        String ime = ime_edit.getText().toString().trim();
        int brb = Integer.valueOf(value);



        if(!TextUtils.isEmpty(ime)){
            Igraci i = new Igraci(brb,ime,id);
            dbigraci.child(id).setValue(i);
            in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();

        }else{
            Toast.makeText(this,"Unesite ime",Toast.LENGTH_LONG).show();
        }
    }
}
