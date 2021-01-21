package com.example.kontrolis2_1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Receiver extends BroadcastReceiver {

    static MainActivity mainActivity;
    String channelId = "kontrolinis2";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent i) {
        Log.i("log", "I RECEIVED");
        String rez = i.getAction();
        Bundle rezExtra = i.getExtras();
        String extraInfo = rezExtra.getString("extraInfo");
        Log.i("log", extraInfo);

        final TextView laukas = (TextView) mainActivity.findViewById(R.id.textView);

        final String pranesimas =
                "Gautas naujas transliavimo pranesimas." +
                        "\nIntentas: " + rez +
                        "\nInfo: " + extraInfo;
//        laukas.setText(pranesimas);

        createChannel();
        notify(context, pranesimas);
    }

    public void notify(Context context, String pranesimas){
        int messageId = 1;
//        wakeScreen(context);

        Log.i("log", "Sending notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelId);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Priimta transliacija!");
        builder.setContentText(pranesimas);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(messageId, builder.build());
    }

//    public void wakeScreen(Context context){
//        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        boolean isScreenOn = Build.VERSION.SDK_INT >= 20 ? pm.isInteractive() : pm.isScreenOn(); // check if screen is on
//        if (!isScreenOn) {
//            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myApp:notificationLock");
//            wl.acquire(3000); //set your time in milliseconds
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = channelId;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);

            NotificationManager notificationManager = mainActivity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
