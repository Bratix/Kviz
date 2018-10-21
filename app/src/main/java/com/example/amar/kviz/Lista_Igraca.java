package com.example.amar.kviz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Lista_Igraca extends AppCompatActivity {


    Comparator <Igraci> sort_po_bodovima = new Comparator<Igraci>() {
        @Override
        public int compare(Igraci o1, Igraci o2) {
            if(o2.broj_bodova != o1.broj_bodova)
                return o2.broj_bodova - o1.broj_bodova;
            else
                return o1.ime.compareTo(o2.ime);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__igraca);

        DatabaseReference dbigraci = FirebaseDatabase.getInstance().getReference("igraci");
        final ArrayList<Igraci> listaigraca = new ArrayList<Igraci>();
        dbigraci.addValueEventListener(new ValueEventListener() {
            ArrayList<String> lista_igraca=new ArrayList<String>();
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot igracSnap: dataSnapshot.getChildren()){
                    Igraci i = igracSnap.getValue(Igraci.class);
                    listaigraca.add(i);
                }

                Collections.sort(listaigraca,sort_po_bodovima);
                int size = listaigraca.size();

                for(int i = 0; i < size;i++){
                    lista_igraca.add(listaigraca.get(i).toString(i+1));
                }
                ListAdapter adapter = new ArrayAdapter<String>(Lista_Igraca.this,android.R.layout.simple_list_item_1,lista_igraca);
                ListView lista_igraca_xml = findViewById(R.id.lista_igraca);
                lista_igraca_xml.setAdapter(adapter);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });


    }

}
