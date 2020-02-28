package com.example.dersyoklama.Program;



public class programDers implements Comparable<programDers> {
    public String dersAdi;
    private int baslamaSaat;
    private int baslamaDak;
    public String saat;

    public programDers(String dersAdi, int baslamaSaat, int baslamaDak) {
        this.dersAdi = dersAdi;
        this.baslamaSaat = baslamaSaat;
        this.baslamaDak = baslamaDak;
        saat =baslamaSaat+":"+baslamaDak;
    }

    public int getBaslamaSaat() {
        return baslamaSaat;
    }

    public void setBaslamaSaat(int baslamaSaat) {
        this.baslamaSaat = baslamaSaat;
    }

    public int getBaslamaDak() {
        return baslamaDak;
    }

    public void setBaslamaDak(int baslamaDak) {
        this.baslamaDak = baslamaDak;
    }




    @Override
    public int compareTo(programDers programDers) {
        int sira =programDers.getBaslamaSaat();
        return this.getBaslamaSaat()-sira;
    }
}
