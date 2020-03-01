package com.example.dersyoklama;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Servis extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    Timer zamanlayici;
    Handler tutucu;
    static long ZAMAN =60000;

    @Override
    public void onCreate() {
        super.onCreate();
        zamanlayici = new Timer();
        tutucu = new Handler(Looper.getMainLooper());

        zamanlayici.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bilgiver();
            }
        },0,ZAMAN);
    }
    public void bilgiver(){
        long zaman = System.currentTimeMillis();
        SimpleDateFormat bilgi = new SimpleDateFormat("EEE-kk-mm");
        String sonuc = bilgi.format(new Date(zaman));

        final String zamanlar [] = sonuc.split("-");
        final String gun =zamanlar[0];
        final String saat =zamanlar[1];
        final String dk =zamanlar[2];

        tutucu.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                System.out.println("Sistem Başladı");
                     try{
                         FileInputStream fileInputStream = openFileInput("AyriDersler.txt");
                         InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
                         BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                         String line =bufferedReader.readLine();

                         while (line!=null){
                             String Dizi[]=line.split("/");
                             System.out.println(Dizi[0]+"-"+Dizi[1]+"-"+Dizi[4]+"-"+Dizi[5]);
                             System.out.println("Sistem saati ="+zamanlar[0]+"-"+zamanlar[1]+"-"+zamanlar[2]);
                             if(gun.equals(Dizi[1].substring(0,3))){

                                 System.out.println("Gun Dogru");
                                 if(saat.equals(Dizi[4])){
                                     System.out.println("Saat Dogru");
                                     if(dk.equals(Dizi[5])){
                                         System.out.println("Dk dogru");
                                         bildirimGonder(Dizi[0]);
                                     }

                                 }

                             }


                             line=bufferedReader.readLine();
                         }

                     }
                     catch (Exception e){}
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void bildirimGonder(String dersAdi){
        Intent intent= new Intent(this, BilgiGir.class);

        DersAdi.dersAdi=dersAdi;

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        String CHANNEL_ID = "Bildirim";
        String CHANNEL_NAME = "Bildirim Ders Takip";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableVibration(true);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);



        int NOTIFICATION_ID = 52;//Notification id si, channel ile ilgisi bulunmuyor
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(dersAdi)
                .setContentText(dersAdi +" dersine girdin mi ?")
                .setSmallIcon(R.drawable.ic_class_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        NotificationManager manager1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager1.notify(NOTIFICATION_ID, notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zamanlayici.cancel();
    }
}
