package com.example.dersyoklama;

public class devamsizlikDers {
    private String dersAdi;
    private String Harf;
    private String DevamHak;
    private String KalanHak;

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getHarf() {
        return Harf;
    }

    public void setHarf(String harf) {
        Harf = harf;
    }

    public String getDevamHak() {
        return DevamHak;
    }

    public void setDevamHak(String devamHak) {
        DevamHak = devamHak;
    }

    public String getKalanHak() {
        return KalanHak;
    }

    public void setKalanHak(String kalanHak) {
        KalanHak = kalanHak;
    }

    public devamsizlikDers(String dersAdi, String devamHak, String kalanHak) {
        this.dersAdi = dersAdi;
        Harf = dersAdi.substring(0,1).toUpperCase();
        DevamHak = devamHak;
        KalanHak = kalanHak;
    }
}
