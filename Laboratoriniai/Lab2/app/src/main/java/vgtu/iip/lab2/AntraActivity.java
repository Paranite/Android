package vgtu.iip.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AntraActivity extends AppCompatActivity {

    EditText ivedimoLaukas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antra);
        ivedimoLaukas = (EditText) findViewById(R.id.ivedimui);
    }

    public void grazintiRezultatus(View w){
        String ivestasTekstas = ivedimoLaukas.getText().toString();
//        TODO sukurti intent, nurodant papildomai grazinamus duomenis ir nustatant, kad rezultatas yra OK
        Intent pirmasLang = new Intent(AntraActivity.this, PirmaActivity.class);
        pirmasLang.putExtra("ivestis", ivestasTekstas);
        setResult(Activity.RESULT_OK, pirmasLang);
        finish();
        Log.i("Veiksmas", "Uzdaromas antras activity. Siunciami duomenys - " + ivestasTekstas);
    }
}
