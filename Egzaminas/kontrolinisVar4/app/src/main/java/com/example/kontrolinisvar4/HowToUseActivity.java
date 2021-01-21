package com.example.kontrolinisvar4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowToUseActivity extends AppCompatActivity {

    TextView info;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use_acitivty);

        info = findViewById(R.id.textView_info);
        String informacija = "Pasirinkite meniu kokio tipo grafika norite braizyti. Iveskite reikiamus formules kintamuosius ir intervala.";
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