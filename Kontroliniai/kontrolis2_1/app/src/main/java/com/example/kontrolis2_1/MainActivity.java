package com.example.kontrolis2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Receiver.mainActivity = this;

//        IntentFilter filtras = new IntentFilter();
//        filtras.addAction("2_Programos_nesist_isreikst_trans");
//        registerReceiver(new Receiver(), filtras);
    }

    public void broadcast(View v){
        final String papild_info = "Hello";
        Intent i = new Intent();
        i.setAction("1_Programos_nesist_trans");
        i.putExtra("appName", "com.example.kontrolis2_1");
        i.putExtra("extraInfo", papild_info);
//        i.putExtra("senderLoc", getApplicationContext().getPackageName());
//        i.putExtra("replyLoc", Receiver.class);
        sendBroadcast(i);
    }

}