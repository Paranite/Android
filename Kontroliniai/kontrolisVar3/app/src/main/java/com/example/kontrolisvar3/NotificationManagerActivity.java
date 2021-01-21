package com.example.kontrolisvar3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class NotificationManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_manager);
    }

    public void stopAlarm(View v){
        Notification.stopAlarm();
    }

    public void exitProgram(View v){
        finishAffinity();
        System.exit(0);
    }
}