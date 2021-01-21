package com.example.kontrolinis1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    EditText lauk_ivest;
    EditText lauk_laik;
    Button submitB;

    String laikas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lauk_ivest = findViewById(R.id.lauk_ivest);
//        lauk_laik = findViewById(R.id.lauk_laikas);
        submitB = findViewById(R.id.buttonSubmit);

    }

    public void pateikti(View v){
        String tekstas = lauk_ivest.getText().toString();
//        String laikas = lauk_laik.getText().toString();
        Intent mainLang = new Intent(MainActivity2.this, MainActivity.class);
        mainLang.putExtra("tekstas", tekstas);
        mainLang.putExtra("laikas", laikas);
        setResult(Activity.RESULT_OK, mainLang);
        finish();
    }

    public void paspaustiLaikas(View v){
        int mHour, mMin;

        Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMin = c.get(Calendar.MINUTE);

        TimePickerDialog timePick = new TimePickerDialog(MainActivity2.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                laikas = hourOfDay + ":" + minute;
            }
        }, mHour, mMin, false);
        timePick.show();
    }

}