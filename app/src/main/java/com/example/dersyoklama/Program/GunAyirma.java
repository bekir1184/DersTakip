package com.example.dersyoklama.Program;

import android.app.Activity;

import com.example.dersyoklama.devamsizlikDers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GunAyirma {
    Activity activity;
    String gun;

    public GunAyirma(Activity activity,String gun){
        this.activity=activity;
        this.gun=gun;

    }
    public  ArrayList<programDers>listeCevir(){
        ArrayList<programDers> yeniliste =new ArrayList<>();

            try {
                FileInputStream fileInputStream = activity.openFileInput("AyriDersler.txt");
                InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

                String line =bufferedReader.readLine();
                while (line!=null){
                    String Dizi[]=line.split("/");
                    line=bufferedReader.readLine();
                    if(Dizi[1].equals(gun)){
                        yeniliste.add(new programDers(Dizi[0],Integer.parseInt(Dizi[2]),Integer.parseInt(Dizi[3])));
                    }
                }


            }
            catch (Exception e){

            }
            Collections.sort(yeniliste);


        return yeniliste;
    }


}
