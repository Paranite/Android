package com.example.kontrolinis2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filtras = new IntentFilter();
        filtras.addAction("1_Programos_nesist_trans");
        registerReceiver(new TransliavimoImtuvas(), filtras);
    }

    public class TransliavimoImtuvas extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String rez = intent.getAction();
            Bundle rezExtras = intent.getExtras();
            String appName = rezExtras.getString("appName");
            String extraInfo = rezExtras.getString("extraInfo");
//            String senderLoc = rezExtras.getString("senderLoc");
//            String replyLoc = rezExtras.getString("replyLoc");

            final TextView laukas = (TextView) findViewById(R.id.textField);

            final String pranesimas =
                    "Gautas naujas transliavimo pranesimas" +
                            "\nIntentas: " + rez +
                            "\nSiuntejas: " + appName +
                            "\nPapildoma informacija: " + extraInfo;

            laukas.setText(pranesimas);

            broadcast(rez);
        }

    }

    public void broadcast(String raktazodis){
        final String papild_info = "Hello. I received ur message " + raktazodis;
        Intent i = new Intent();
        i.setAction("2_Programos_nesist_isreikst_trans");
        i.putExtra("extraInfo", papild_info);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(i, 0);
        Log.i("log", "Searching for receiver with key");
        for (ResolveInfo info : infos) {
            Log.i("log", info.toString());
            ComponentName cn = new ComponentName(info.activityInfo.packageName,
                    info.activityInfo.name);
            i.setComponent(cn);
            sendBroadcast(i);
        }

        Log.i("log", "Sent broadcast");
    }
}
