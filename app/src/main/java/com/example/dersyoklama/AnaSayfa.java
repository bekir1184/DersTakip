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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_ana_sayfa, container, false);



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

        setFragment2(new DersYok());

        return rootView;

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLay,fragment);
        fragmentTransaction.commit();
    }
    public void setFragment2 (Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FrameTutucu,fragment);
        fragmentTransaction.commit();
    }





}
