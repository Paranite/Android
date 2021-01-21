package vgtu.iip.lab2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PirmaActivity extends AppCompatActivity {

    final static int IVEDIMAS = 1;
    TextView isvedimoLaukas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirma);
        isvedimoLaukas = (TextView) findViewById(R.id.tekstas);
    }

    public void atvertiVeiklaRezultatuGavimui(View w){
//        TODO sukurti intent ir ji paleisti, laukiant rezultatu
        Intent antrasLangas = new Intent(PirmaActivity.this, AntraActivity.class);
        startActivityForResult(antrasLangas, 1);
        Log.i("Veiksmas", "Pradedamas antras activity");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        TODO patikrinti nuo kokios veiklos ir kokius rezult
        String result = data.getStringExtra("ivestis");
//        TODO Rezultatus gavo, tada pasiimti siunciama reiksme ir ja paduoti vietoj sekancioje eiluteje irasyto teksto
        isvedimoLaukas.setText(result);
        Log.i("Veiksmas", "Grizo duomenys; Kodas - " + resultCode +"; Duomenys - " + result);
    }

    public void sukurtiExplicitIntent(View w){
        String tekstas = isvedimoLaukas.getText().toString();
//        TODO sukurti explicit intent, su putExtra perduoti tekstas kintamaji ir tada paleisti, nelaukiant rezultatu
        Intent treciasLang = new Intent(PirmaActivity.this, TreciaActivity.class);
        treciasLang.putExtra("ivestis", tekstas);
        treciasLang.setAction(Intent.ACTION_SEND);
        startActivity(treciasLang);
        Log.i("Veiksmas", "Atidaromas trecias activity. Siunciami duomenis - " + tekstas);
    }
    public void sukurtiImplicitIntent(View w){
        String duomenysSiuntimui = isvedimoLaukas.getText().toString();
//        TODO sukurti explicit intent, jame perduoti kintamaji duomenysSiuntimui (tekstiniai duomenys)
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, duomenysSiuntimui);
        sendIntent.setType("text/plain");
//        TODO tada kitas intent bus tam, kad vartotojas galetu rinktis norima programa ir ja paleisti nelaukaint rezultatu
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);


    }
}
