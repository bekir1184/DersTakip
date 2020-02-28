package com.example.dersyoklama;

import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class pagerModel {
    String baslik;
    List list;

    public pagerModel(String baslik, List list) {
        this.baslik = baslik;
        this.list = list;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
