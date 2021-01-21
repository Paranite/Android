package com.example.nesist_stat_trnasl_ir_imtuv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class Transl_Imtuvas_4 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        String intentas = i.getAction();
        Bundle rezExtra = i.getExtras();
        String rezExtra1 = rezExtra.getString("Nesist_transl_pranesimas");
//        Log.i("rezultatas -", "---------- Gauta tarnsliacija: Stat_Transl_Imtuvas ----------");
//        Log.i("rezultatas - gauto intento pavadinimas: ", rez);
//        Log.i("rezultatas - papildoma intento informacija", rezExtra1);


        //String pranesimas = "Gauta tarnsliacija: Stat_Transl_Imtuvas" +
        //        " gauto intento pavadinimas:" + rez +
         //       " papildoma intento informacija" + rezExtra1;
//        laukas.setText(pranesimas);

        Toast.makeText(context, "Gauta transliacija į 'Transl_Imtuvas_4 (Statiškai registruotą)'" +
                "\nIntentas: " + intentas +
                "\n" + rezExtra1,
                Toast.LENGTH_LONG ).show();

    }
}
