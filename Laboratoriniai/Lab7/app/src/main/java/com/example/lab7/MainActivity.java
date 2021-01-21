package com.example.lab7;

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
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText battery_percentage;
    EditText time;
    String channelName = "Channel1";
    int notificationId = 1;
    Switch alarm;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        battery_percentage = findViewById(R.id.editTextProcentai);
        time = findViewById(R.id.editTextLaikas);
        alarm = findViewById(R.id.switchPranesti);
        createChannel();

        Intent intent = new Intent(this, Notification.class);
        pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = channelName;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelName, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notify(View v){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelName);
        builder.setSmallIcon(R.drawable.arrow);
        builder.setContentTitle("Senka baterija!");
        builder.setContentText("Telefone liko maziau nei " + battery_percentage.getText() + "% baterijos");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(notificationId, builder.build());
    }

    public void alarmSet(View v){
        if (alarm.isChecked()) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            long period = Long.parseLong(String.valueOf(time.getText()));
            alarmManager.setRepeating(
                    AlarmManager.RTC,
                    System.currentTimeMillis() + period,
                    period,
                    pendingIntent
            );
        }else{
                if(pendingIntent != null && alarmManager != null){
                    alarmManager.cancel(pendingIntent);
            }
        }
    }
}