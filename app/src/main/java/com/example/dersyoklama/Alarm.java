package com.example.dersyoklama;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle= intent.getExtras();
        String dersAdi=bundle.getString("dersAdi");

        Uri ses = RingtoneManager.getActualDefaultRingtoneUri(context,RingtoneManager.TYPE_NOTIFICATION);

        Ringtone ringtone =RingtoneManager.getRingtone(context,ses);

        ringtone.play();

        NotHelper notificationHelper = new NotHelper(context,dersAdi);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());



    }
}
