package com.example.nesist_stat_trnasl_ir_imtuv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mygtukas1;
    Button mygtukas2;
    Button mygtukas3;
    Button mygtukas4;

    static MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        Transl_Imtuvas_3.mainActivity = this;

        // 1 transliavimo imtuvo "Transl_Imtuvas_1" filtrai ir registravimas
        // Sukuriamas filtras filtras1
        // su kuriuo mano "Transl_Imtuvas_1" gaudys pranesimus
        IntentFilter filtras1 = new IntentFilter("Mano_nesisteminis_Intentas");
        // pridedamas raktinis zodis "Mano_nesisteminis_Intentas" pagal kuri
        // atpazystama kad si transliacija skirta siam imtuvui paleidziamas Imtuvo metodas onReceive()
        filtras1.addAction("Mano_nesisteminis_Intentas");
        // Uzregistruojamas transliavimo imtuvas "Transl_Imtuvas_1()" su filtru "filtras1"
        registerReceiver(new Transl_Imtuvas_1(), filtras1);

        // 2 transliavimo imtuvo "Transl_Imtuvas_2" filtrai ir registravimas
        // Sukuriamas filtras "Mano_nesisteminis_Intentas" su kuriuo
        // transliavimo imtuvas "Transl_Imtuvas_2()" gaudys pranesima
        IntentFilter filtras2 = new IntentFilter();
        // Pridedamas raktinis zodis "Mano_nesisteminis_Intentas" pagal kuri
        // atpazystama kad si transliacija skirta siam imtuvui paleidziamas Imtuvo metodas onReceive()
        filtras2.addAction("Mano_nesisteminis_Intentas");
        // Pridedamas kitas papildomas raktinis zodis "Mano_nesisteminis_Intentas_2" pagal kuri
        // taip pat bus atpazystama kad jo laukia "Transl_Imtuvas_2"
        filtras2.addAction("Mano_nesisteminis_Intentas_2");
        // Uzregistruojamas transliavimo imtuvas "Transl_Imtuvas_2()" su filtru "filtras2"
        this.registerReceiver(new Transl_Imtuvas_2(), filtras2);

        // 3 transliavimo imtuvo "Transl_Imtuvas_3" filtrai ir registravimas
        // Sukuriamas filtras "Mano_nesisteminis_Intentas" su kuriuo
        // transliavimo imtuvas "Transl_Imtuvas_3()" gaudys pranesima
        IntentFilter filtras3 = new IntentFilter();
        // Pridedamas raktinis zodis "Mano_nesisteminis_Intentas" pagal kuri
        // atpazystama kad si transliacija skirta siam imtuvui paleidziamas Imtuvo metodas onReceive()
        filtras3.addAction("Mano_nesisteminis_Intentas");
        // Pridedamas kitas papildomas raktinis zodis "Mano_nesisteminis_Intentas_3" pagal kuri
        // taip pat bus atpazystama kad jo laukia "Transl_Imtuvas_3"
        filtras3.addAction("Mano_nesisteminis_Intentas_3");
        // Uzregistruojamas transliavimo imtuvas "Transl_Imtuvas_3()" su filtru "filtras3"
        this.registerReceiver(new Transl_Imtuvas_3(), filtras3);

        mygtukas1 = findViewById(R.id.Transl_1_mygt);
        mygtukas2 = findViewById(R.id.Transl_2_mygt);
        mygtukas3 = findViewById(R.id.Transl_3_mygt);
        mygtukas4 = findViewById(R.id.Transl_4_mygt);
        TextView laukas = (TextView) findViewById(R.id.laukas_1);

    }


    public void Istransliuok_1_transliacija(View view) {
        // Metodasd transliuoja nesistemini intwenta
        final String Nesistem_transl_1_pranesimas = "Intentas: 'Mano_nesisteminis_Intentas' istransliuotas";
        // Intent objekto sukurimas
        Intent i = new Intent();
        i.setAction("Mano_nesisteminis_Intentas");
        i.putExtra("Nesist_transl_pranesimas", Nesistem_transl_1_pranesimas);
        sendBroadcast(i);

        // Mygtuko paspaudimo metodo kvietimas
        {
            mygtukas1.setText(Nesistem_transl_1_pranesimas);
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                    mygtukas1.setText(Nesistem_transl_1_pranesimas + " " +
                            millisUntilFinished / 1000 + " s");
                }

                public void onFinish() {
                    mygtukas1.setText("1. Transliavimas");
                }
            }.start();
        }

        Log.i("rezultatas", Nesistem_transl_1_pranesimas);
    }

    public void Istransliuok_2_transliacija(View view) {
        // Metodas transliuoja nesistemini intenta
        final String Nesistem_transl_2_pranesimas = "Intentas: 'Mano_nesisteminis_Intentas_2' istransliuotas";
        // Intent objekto sukurimas
        Intent i = new Intent();
        //i.setClass(this, Transl_Imtuvas_1.class);
        i.setAction("Mano_nesisteminis_Intentas_2");
        //i.setAction("Mano_nesisteminis_Intentas");

        i.putExtra("Nesist_transl_pranesimas", Nesistem_transl_2_pranesimas);
        sendBroadcast(i);

        // Mygtuko paspaudimo metodo kvietimas
        {
            mygtukas2.setText(Nesistem_transl_2_pranesimas);
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                    mygtukas2.setText(Nesistem_transl_2_pranesimas + " " +
                            millisUntilFinished / 1000 + " s");
                }

                public void onFinish() {
                    mygtukas2.setText("2. Transliavimas");
                }
            }.start();
        }

        Log.i("rezultatas", Nesistem_transl_2_pranesimas);
    }

    public void Istransliuok_3_transliacija(View view) {
        // Metodas transliuoja nesistemini isreikštinį intenta
        // Klasė kuriai transliuoja intentas "Transl_Imtuvas_3"
        final String Nesistem_transl_3_pranesimas = "Intentas: 'Mano_nesisteminis_Intentas_3' istransliuotas";
        // Intent objekto sukurimas
        //Intent i = new Intent(getApplicationContext(), Transl_Imtuvas_3.class);
        // (1) setClass() taikymas
        Intent i = new Intent();
        // (1) setClass() taikymas
        i.setClass(this, Transl_Imtuvas_3.class); // variantas su this

        // (2) setClassName() taikymas
        //i.setClassName(getApplicationContext(), Transl_Imtuvas_3.class); // variantas su getApplicationContext()
        //String classname = Transl_Imtuvas_3.class.getName(); // isgaunamas klases pavadinimas
        //Log.i("klases pavad", classname);
        //i.setClassName(this, classname); // // variantas su setClassName

        // (3) setPackage() taikymas
        // issiuncia transliavimus visai programai is karto ir visoms jos klasems
        //String programos_pavad = i.getPackage();
        //i.setPackage("com.example.nesist_stat_trnasl_ir_imtuv");
        //i.setPackage(programos_pavad);
        // -----------------------------------------------------
        i.setAction("Mano_nesisteminis_Intentas_3");
        i.setAction("Mano_nesisteminis_Intentas_2");
        i.setAction("Mano_nesisteminis_Intentas");
        i.putExtra("Nesist_transl_pranesimas", Nesistem_transl_3_pranesimas);
        sendBroadcast(i);

        // Mygtuko paspaudimo metodo kvietimas
        {
            mygtukas3.setText(Nesistem_transl_3_pranesimas);
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                    mygtukas3.setText(Nesistem_transl_3_pranesimas + " " +
                            millisUntilFinished / 1000 + " s");
                }

                public void onFinish() {
                    mygtukas3.setText("3. Transliavimas");
                }
            }.start();
        }

        Log.i("rezultatas", Nesistem_transl_3_pranesimas);
    }

    public void Istransliuok_4_transliacija(View view) {
        // Metodas transliuoja nesistemini isreikštinį intenta
        // Klasė kuriai transliuoja intentas "Transl_Imtuvas_4"
        final String Nesistem_transl_4_pranesimas = "Intentas: 'Mano_nesisteminis_Intentas_4' istransliuotas";
        // Intent objekto sukurimas
        Intent i = new Intent();
        i.setClass(this, Transl_Imtuvas_4.class);
        i.setAction("Mano_nesisteminis_Intentas");
        i.putExtra("Nesist_transl_pranesimas", Nesistem_transl_4_pranesimas);
        sendBroadcast(i);

        // Mygtuko paspaudimo metodo kvietimas
        {
            mygtukas4.setText(Nesistem_transl_4_pranesimas);
            new CountDownTimer(5000, 1000) {
                public void onTick(long millisUntilFinished) {
                    mygtukas4.setText(Nesistem_transl_4_pranesimas + " " +
                            millisUntilFinished / 1000 + " s");
                }

                public void onFinish() {
                    mygtukas4.setText("4. Transliavimas");
                }
            }.start();
        }

        Log.i("rezultatas", Nesistem_transl_4_pranesimas);
    }

    public class Transl_Imtuvas_1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent i) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            // throw new UnsupportedOperationException("Not yet implemented");
            String rez = i.getAction();
            Bundle rezExtra = i.getExtras();
            String rezExtra1 = rezExtra.getString("Nesist_transl_pranesimas");

            Log.i("rezultatas -", "---------- Gauta tarnsliacija: Stat_Transl_Imtuvas_1 ----------");
            Log.i("rezultatas - gauto intento pavadinimas: ", rez);
            Log.i("rezultatas - papildoma intento informacija", rezExtra1);

            // Gautos transiacijos informacijos išvedimas i GVS TexView lauka
            {
                final TextView laukas = (TextView) findViewById(R.id.laukas_1);


                final String pranesimas =
                        "Gauta nauja tarnsliacija" +
                                "\n Intento pavadinimas: " + rez +
                                " \n Papildoma intento informacija\n" + rezExtra1;
                laukas.setText(pranesimas);

                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        laukas.setText(pranesimas);
                    }

                    public void onFinish() {
                        laukas.setText("Laukiamas veiksmas");
                        ;
                    }
                }.start();
            }

        } // onReceive metodo pabaiga
    } // Transl_Imtuvas_1 (BroadcastReceiver vaikas) klases pabaiga

    public class Transl_Imtuvas_2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent i) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            // throw new UnsupportedOperationException("Not yet implemented");
            String rez = i.getAction();
            Bundle rezExtra = i.getExtras();
            String rezExtra1 = rezExtra.getString("Nesist_transl_pranesimas");
            //String rezExtra1 = "";

            Log.i("Bundle rezultatas -", rez);
            //String rezExtra1 = rezExtra.getString("Nesist_transl_1_pranesimas");

            Log.i("rezultatas -", "---------- Gauta tarnsliacija: Stat_Transl_Imtuvas_2 ----------");
            Log.i("rezultatas - gauto intento pavadinimas: ", rez);
            Log.i("rezultatas - papildoma intento informacija", rezExtra1);

            // Gautos transiacijos informacijos išvedimas i GVS TexView lauka
            {
                final TextView laukas = (TextView) findViewById(R.id.laukas_2);

                final String pranesimas =
                        "Gauta nauja tarnsliacija" +
                                "\n Intento pavadinimas: " + rez +
                                " \n Papildoma intento informacija\n" + rezExtra1;
                laukas.setText(pranesimas);

                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        laukas.setText(pranesimas);
                    }

                    public void onFinish() {
                        laukas.setText("Laukiamas veiksmas");
                        ;
                    }
                }.start();
            }

        } // onReceive metodo pabaiga
    } // Transl_Imtuvas_1 (BroadcastReceiver vaikas) klases pabaiga

}