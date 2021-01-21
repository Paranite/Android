package com.example.a3lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText laukasPav;
    AutoCompleteTextView laukasFakultetas;
    RatingBar reitingas;
    Spinner laukasDiena;
    EditText laukasLaikas;
    Switch registruotis;
    Button saugoti;

    final static String[] fakultetai = new String[] {
            "AIF - Aplinkos inžinerijos fakultetas", "AF - Architektūros fakultetas", "EF - Elektronikos fakultetas", "FMF - Fundamentinių mokslų fakultetas", "KIF - Kūrybinių industrijų fakultetas", "MF - Mechanikos fakultetas", "SF -Statybos fakultetas", "TIF - Transporto inžinerijos fakultetas", "VVF - Verslo vadybos fakultetas"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laukasPav = findViewById(R.id.laukasPavadinimas);
        laukasFakultetas = findViewById(R.id.laukasFakultetas);
        reitingas = findViewById(R.id.ratingBar);
        laukasDiena = findViewById(R.id.laukasDiena);
        laukasLaikas = findViewById(R.id.laukasLaikas);
        registruotis = findViewById(R.id.switchRegistruotis);
        saugoti = findViewById(R.id.mygtukasSaugoti);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fakultetai);
        laukasFakultetas.setAdapter(adapter);

    }

    public void paspaustiSaugoti(View w){
        String info = "";
        info = "Pavadinimas: " + laukasPav.getText() + " | Fakultetas: " + laukasFakultetas.getText() + " | Reitingas: " + reitingas.getRating() + " | Diena: " + laukasDiena.getSelectedItem() +
            " | Laikas: " + laukasLaikas.getText() + " | Registruotis: " + registruotis.isChecked();
        System.out.println(info);
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }
}