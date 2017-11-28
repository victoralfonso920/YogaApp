package com.devs.victorhernandez.yogafitness.Api;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.devs.victorhernandez.yogafitness.R;

/**
 * Created by victorhernandez on 17/9/17.
 */

@RequiresApi(api = VERSION_CODES.O)
public class AlarmNotificationReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("It's time")
                .setContentText("Time to trining")
                .setContentInfo("Info");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "defaultChannel";
            String channelName = "Channel Name";
            String channelDescription = "Channel Description";
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            builder.setChannelId(channelId);

        }

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(1,builder.build());

    }
}
