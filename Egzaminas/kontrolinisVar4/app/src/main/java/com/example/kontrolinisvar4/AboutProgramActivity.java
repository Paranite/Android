package com.example.kontrolinisvar4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutProgramActivity extends AppCompatActivity {

    TextView info;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_program);

        info = findViewById(R.id.textView_info);
        String informacija = "Šioje programoje galima braižyti meniu pasirinktą grafiką pagal žemiau įvestus formulės kintamuosius ir nurodyta intervalą.";
        info.setText(informacija);
        button = findViewById(R.id.button_close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}