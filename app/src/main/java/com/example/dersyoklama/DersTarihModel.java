package com.example.dersyoklama;

import java.util.Date;

public class DersTarihModel {
    int dersNo;
    String gun ;


    int baslamaSaat;
    int baslamaDakika;

    int bitisSaat;
    int bitisDakika;

    public DersTarihModel(int dersNo,String gun, int baslamaSaat, int baslamaDakika, int bitisSaat, int bitisDakika) {
        this.dersNo=dersNo;
        this.gun = gun;
        this.baslamaSaat = baslamaSaat;
        this.baslamaDakika = baslamaDakika;
        this.bitisSaat = bitisSaat;
        this.bitisDakika = bitisDakika;
    }

}
