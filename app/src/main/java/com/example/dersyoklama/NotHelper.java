package com.example.dersyoklama;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private String dersAdi;


    private NotificationManager mManager;

    public NotHelper(Context base,String dersAdi) {
        super(base);

        this.dersAdi=dersAdi;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }

    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    @SuppressLint("ResourceAsColor")
    public NotificationCompat.Builder getChannelNotification() {

        Intent intent= new Intent(this, BilgiGir.class);
        intent.putExtra("dersAdi",dersAdi);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);


        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(dersAdi)
                .setContentText("Derse girdin mi ?")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(R.color.turuncuAktif)
                .setSmallIcon(R.drawable.ic_class_black_24dp);
    }

}
