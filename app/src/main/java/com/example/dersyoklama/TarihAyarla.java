package com.example.dersyoklama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class TarihAyarla extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    TextView dersNoTV;
    Button basButton;
    Button bitButton;
    boolean tusKontrol;
    static String gun;
    Button ekleButton;
    static int baslamaSaat;
    static int baslamaDak;
    static int bitisSaat;
    static int bitisDak;
    static int dersNo=1;
    public boolean dersAtla=true;
    static ArrayList<DersTarihModel>models =new ArrayList<>();

    FileOutputStream fileOutputStream;
    FileOutputStream ayridersOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(dersNo >= Integer.parseInt(String.valueOf(DersEkle.getDersSaat()))){
            dersAtla=false;
        }
        setContentView(R.layout.activity_tarih);
        dersNoTV =(TextView)findViewById(R.id.tarihBaslikText);
        dersNoTV.setText(dersNo+".Dersin Tarihini Giriniz");

        basButton =(Button)findViewById(R.id.baslamaSaatButton);
        bitButton =(Button)findViewById(R.id.bitisSaatButton);
        ekleButton=(Button)findViewById(R.id.dersEkleButton);

        Spinner spinner = findViewById(R.id.gunlerSpinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.gunler,R.layout.spinner_item_custom);
        adapter.setDropDownViewResource(R.layout.spinner_drop);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        basButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time");
                tusKontrol=true;
            }
        });


        bitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time");
                tusKontrol=false;
            }
        });

        ekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dersAtla){

                    models.add(new DersTarihModel(dersNo,gun,baslamaSaat,baslamaDak,bitisSaat,bitisDak));
                    dersNo++;

                    //Alarm Ayarlama
                    Calendar calNow = Calendar.getInstance();
                    Calendar calSet = (Calendar) calNow.clone();

                    calSet.set(Calendar.DAY_OF_WEEK,gunAyarla(gun));
                    calSet.set(Calendar.HOUR_OF_DAY,bitisSaat);
                    calSet.set(Calendar.MINUTE, bitisDak+5);
                    calSet.set(Calendar.SECOND, 0);
                    calSet.set(Calendar.MILLISECOND, 0);

                    startAlarm(calSet,DersEkle.getDersAdi());

                    Intent intent = new Intent(TarihAyarla.this, TarihAyarla.class);
                    startActivity(intent);

                }
                else{
                    models.add(new DersTarihModel(dersNo,gun,baslamaSaat,baslamaDak,bitisSaat,bitisDak));
                    Ders ders = new Ders(DersEkle.getDersAdi(),DersEkle.getDersDevamHak(),models);

                    //Alarm Ayarlama
                    Calendar calNow = Calendar.getInstance();
                    Calendar calSet = (Calendar) calNow.clone();

                    calSet.set(Calendar.DAY_OF_WEEK,gunAyarla(gun));
                    calSet.set(Calendar.HOUR_OF_DAY,bitisSaat);
                    calSet.set(Calendar.MINUTE, bitisDak);
                    calSet.set(Calendar.SECOND, 0);
                    calSet.set(Calendar.MILLISECOND, 0);


                    startAlarm(calSet,DersEkle.getDersAdi());


                    //Dosyaya yazma
                    try {

                        fileOutputStream =openFileOutput("Dersler.txt",MODE_APPEND);
                        ayridersOS =openFileOutput("AyriDersler.txt", Context.MODE_APPEND);

                        fileOutputStream.write(ders.DersAdi.getBytes());
                        fileOutputStream.write("/".getBytes());

                        System.out.println(ders.DersAdi+"-->Dersler");

                        fileOutputStream.write(Integer.toString(ders.dersDevamsizlikHakki).getBytes());
                        fileOutputStream.write("/".getBytes());

                        System.out.println(ders.dersDevamsizlikHakki+"-->Dersler");
                        fileOutputStream.write(Integer.toString(ders.yapilanDevamsizlik).getBytes());
                        fileOutputStream.write("/".getBytes());

                        System.out.println(ders.yapilanDevamsizlik+"-->Dersler");

                        fileOutputStream.write(Integer.toString(DersEkle.getDersSaat()).getBytes());
                        fileOutputStream.write("/".getBytes());

                        System.out.println(DersEkle.getDersSaat()+"-->Dersler");

                        for (DersTarihModel model :models){

                            ayridersOS.write(ders.DersAdi.getBytes());
                            ayridersOS.write("/".getBytes());
                            System.out.println(ders.DersAdi+"-->AyrıDersler");

                            fileOutputStream.write(Integer.toString(model.dersNo).getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.dersNo+"-->Dersler");

                            fileOutputStream.write(model.gun.getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.gun+"-->Dersler");

                            ayridersOS.write(model.gun.getBytes());
                            ayridersOS.write("/".getBytes());

                            System.out.println(model.gun+"-->AyrıDersler");

                            fileOutputStream.write(Integer.toString(model.baslamaSaat).getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.baslamaSaat+"-->Dersler");

                            ayridersOS.write(Integer.toString(model.baslamaSaat).getBytes());
                            ayridersOS.write("/".getBytes());

                            System.out.println(model.baslamaSaat+"-->AyrıDersler");

                            fileOutputStream.write(Integer.toString(model.baslamaDakika).getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.baslamaDakika+"-->Dersler");

                            ayridersOS.write(Integer.toString(model.baslamaDakika).getBytes());
                            ayridersOS.write("/".getBytes());

                            System.out.println(model.baslamaDakika+"-->AyrıDersler");


                            fileOutputStream.write(Integer.toString(model.bitisSaat).getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.bitisSaat+"-->Dersler");
                            fileOutputStream.write(Integer.toString(model.bitisDakika).getBytes());
                            fileOutputStream.write("/".getBytes());

                            System.out.println(model.bitisDakika+"-->Dersler");

                            ayridersOS.write("\n".getBytes());
                            System.out.println("----------------------");

                        }

                        System.out.println("Bitti");
                        fileOutputStream.write("\n".getBytes());
                        fileOutputStream.close();
                        ayridersOS.close();

                        models.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                    //Ana Sayfaya Donme
                    dersNo=1;
                    Toast.makeText(getApplicationContext(),"Ders Eklendi",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TarihAyarla.this,MainActivity.class);
                    startActivity(intent);

                }
            }
        });

    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        gun =adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if(tusKontrol){
            basButton.setText(i+" : "+i1);
            baslamaSaat=i;
            baslamaDak=i1;
        }
        else{
            bitButton.setText(i+" : "+i1);
            bitisSaat=i;
            bitisDak=i1;
        }
    }
    public int gunAyarla(String str){
        if(str.equals("Pazartesi")){
            return Calendar.MONDAY;
        }
        else if(str.equals("Sali")){
            return Calendar.TUESDAY;
        }
        else if(str.equals("Carsamba")){
            return Calendar.WEDNESDAY;
        }
        else if(str.equals("Persembe")){
            return Calendar.THURSDAY;
        }
        else if(str.equals("Cuma")){
            return Calendar.FRIDAY;
        }
        else if(str.equals("Cumartesi")){
            return Calendar.SATURDAY;
        }
        else {
            return Calendar.SUNDAY;
        }


    }
    private void startAlarm(Calendar c,String dersAdi) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        intent.putExtra("dersAdi",dersAdi);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

    }
    public static void cancelAlarm(Activity activity) {
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(activity, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 1, intent, 0);



        alarmManager.cancel(pendingIntent);

    }


}
