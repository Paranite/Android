package vgtu.iip.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static android.content.Intent.ACTION_SEND;

public class TreciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trecia);
        TextView laukasIsvedimui = (TextView) findViewById(R.id.rezultatas);

        String tekstas = "";
        //TODO reiketu patikrinti ar veiksmas buvo ACTION_SEND ar ne, ir pasiimti siunciamus duomenis, kaip kintamaji tekstas
        if(this.getIntent().getAction() == ACTION_SEND){
            Log.i("ACTION PATIKRA", "Action yra ACTION_SEND");
        }
        else{
            Log.i("ACTION PATIKRA", "Action nera ACTION_SEND");
        }
        tekstas = (String) this.getIntent().getSerializableExtra("ivestis");
        Log.i("Veiksmas", "Gauti duomenys - " + tekstas);
        int kiekis = tekstas.split(" ").length;
        String isvestis = getString(R.string.ivestasTestas)+" '"+tekstas+"' "+getString(R.string.yra)
                +" "+kiekis+" "+getString(R.string.zodziu);
        laukasIsvedimui.setText(isvestis);
    }
}
