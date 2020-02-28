package com.example.dersyoklama.Program;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dersyoklama.Program.Adapterlar.FragmentAdapter;
import com.example.dersyoklama.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class Prog extends Fragment {
    private View rootView;

    ViewPager viewPager;
    TabLayout tabLayout;
    Context context =getContext();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_prog, container, false);

        tabLayout =rootView.findViewById(R.id.tab_lay);
        viewPager =rootView.findViewById(R.id.viewProg);

        viewPager.setAdapter(new FragmentAdapter(getActivity().getSupportFragmentManager(),context));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                System.out.println(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return rootView;
    }


}
