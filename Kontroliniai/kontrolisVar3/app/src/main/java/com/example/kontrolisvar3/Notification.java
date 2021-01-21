package com.example.kontrolisvar3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification extends BroadcastReceiver {

    String channelName = "kontrolinis2";
    int messageId = 1;
    static boolean isAlarmPlaying = false;
    static MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("log", "Broadcast received");
        notify(context);
    }

    public void notify(Context context){
        playAlarm(context);
        wakeScreen(context);
        Log.i("log", "Sending notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelName);
        builder.setSmallIcon(R.drawable.arrow);
        builder.setContentTitle("Atejo numatytas laikas!");
        builder.setContentText("Numatytas laikas buvo: " + MainActivity.startTime);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context.getApplicationContext(), NotificationManagerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(messageId, builder.build());
    }

    public void playAlarm(Context context){
        if(!isAlarmPlaying) {
            Uri alarmSound =
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mp = MediaPlayer.create(context.getApplicationContext(), alarmSound);
            mp.start();
            isAlarmPlaying = true;
        }
    }

    public static void stopAlarm(){
        isAlarmPlaying = false;
        mp.stop();
    }

    public void wakeScreen(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = Build.VERSION.SDK_INT >= 20 ? pm.isInteractive() : pm.isScreenOn(); // check if screen is on
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myApp:notificationLock");
            wl.acquire(3000); //set your time in milliseconds
        }
    }
}
