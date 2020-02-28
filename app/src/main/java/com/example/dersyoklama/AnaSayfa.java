package com.example.dersyoklama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnaSayfa extends Fragment {
    private View rootView;
    private ViewPager viewPager;
    private int sayfa=0;
    private ViewPagerAdepter pagerAdepter;
    List<pagerModel> liste;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_ana_sayfa, container, false);

        List<String>deneme=new ArrayList<>();
        deneme.add("Matemetik");
        deneme.add("Matemetik");
        deneme.add("Matemetik");

        liste= new ArrayList<>();
        liste.add(new pagerModel("Yaklaşan Dersler",  deneme));
        liste.add(new pagerModel("Yaklaşan Sınavlar",deneme));
        liste.add(new pagerModel("Devamsizlik Durumu",deneme));
        viewPager=rootView.findViewById(R.id.viewPagerAnaSayfa);



        pagerAdepter= new ViewPagerAdepter(liste,getContext());

        viewPager.setAdapter(pagerAdepter);
        viewPager.setPadding(50,0,50,0);



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(final int position) {
                sayfa=position;


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.dersEkleFloat);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new DersEkle());
            }
        });

        FloatingActionButton floatingActionButton1 = rootView.findViewById(R.id.sinavEkleFloat);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SinavEkle());
            }
        });


        return rootView;

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLay,fragment);
        fragmentTransaction.commit();
    }




}
