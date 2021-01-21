package com.example.lab7;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification extends BroadcastReceiver {

    String channelName = "Channel1";
    int messageId = 1;
    float batteryPct;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("log", "Broadcast received");
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        batteryPct = level * 100 / (float) scale;
        Log.i("log", "BatteryPct: " + batteryPct);
        if(batteryPct <= 20){
            notify(context);
        }
    }

    public void notify(Context context){
        Log.i("log", "Sending notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelName);
        builder.setSmallIcon(R.drawable.arrow);
        builder.setContentTitle("Senka baterija! Maziau 20% baterijos");
        builder.setContentText("Telefone liko " + batteryPct + "% baterijos");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(messageId, builder.build());
    }
}
