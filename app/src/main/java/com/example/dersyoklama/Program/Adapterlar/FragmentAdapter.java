package com.example.dersyoklama.Program.Adapterlar;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dersyoklama.Program.Fragmentlar.Carsamba;
import com.example.dersyoklama.Program.Fragmentlar.Cuma;
import com.example.dersyoklama.Program.Fragmentlar.Cumartesi;
import com.example.dersyoklama.Program.Fragmentlar.Pazartesi;
import com.example.dersyoklama.Program.Fragmentlar.Persembe;
import com.example.dersyoklama.Program.Fragmentlar.Sali;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private String [] tabTitle ={"Pazartesi","Sali","Çarşamba","Perşembe","Cuma","Cumartesi"};
    Context context;



    public FragmentAdapter(FragmentManager fm, Context context ) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {



        switch (position){
            case 0 :

                return new Pazartesi();
            case 1 :

                return new Sali();
            case 2 :

                return new Carsamba();
            case 3 :

                return  new Persembe();
            case 4 :

                return new Cuma();
            case 5 :

                return  new Cumartesi();


            default:

                return new Pazartesi();

        }


    }

    @Override
    public int getCount() {
        return tabTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        return tabTitle[position];
    }
}
