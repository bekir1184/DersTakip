package com.example.dersyoklama;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Ders  {
    String DersAdi;
    int dersDevamsizlikHakki;
    ArrayList<DersTarihModel>models;
    int yapilanDevamsizlik;


    public Ders(String dersAdi, int dersDevamsizlikHakki, ArrayList<DersTarihModel>models) {
        DersAdi = dersAdi;
        this.dersDevamsizlikHakki = dersDevamsizlikHakki;
        this.models=models;


    }


}
