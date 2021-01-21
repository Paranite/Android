package com.example.kontrolisvar3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static long period = 1000;
    public static String startTime;
    String channelId = "kontrolinis2";
    boolean isAlarmSet = false;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createChannel();

        Intent intent = new Intent(this, Notification.class);
        pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = channelId;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void toggleAlarm(View v) {
        if (!isAlarmSet) {

            Calendar calendar = Calendar.getInstance();
//            Calendar calendarClone = (Calendar) calendar.clone();
//            calendarClone.add(Calendar.MINUTE, 1);
//            startTime = String.valueOf(calendarClone.getTime());
//            long notificationTime = calendarClone.getTimeInMillis();

            Calendar specificTimeCalendar = (Calendar) calendar.clone();
            specificTimeCalendar.set(Calendar.HOUR_OF_DAY, 12);
            specificTimeCalendar.set(Calendar.MINUTE, 39);
            specificTimeCalendar.set(Calendar.SECOND, 0);
            startTime = String.valueOf(specificTimeCalendar.getTime());
            long notificationTime = specificTimeCalendar.getTimeInMillis();
            // System.currentTimeMillis() + period

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    notificationTime,
                    period,
                    pendingIntent
            );
            isAlarmSet = true;
            Log.i("log", "Alarm on");
        } else {
            if (pendingIntent != null && alarmManager != null) {
                alarmManager.cancel(pendingIntent);
                Log.i("log", "Alarm off");
            }
        }
    }
}