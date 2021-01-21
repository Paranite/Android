package com.example.nesist_stat_trnasl_ir_imtuv;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class Transl_Imtuvas_3 extends BroadcastReceiver {

    static MainActivity mainActivity;

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

        Toast.makeText(context, "Gauta transliacija į 'Transl_Imtuvas_3'" +
                "\nIntentas: " + intentas +
                "\n" + rezExtra1,
                Toast.LENGTH_LONG ).show();

        TextView l  = MainActivity.mainActivity.findViewById(R.id.laukas_1);
        l.setText("Radau");

//        String stringas = ToString(5);
    }
}
