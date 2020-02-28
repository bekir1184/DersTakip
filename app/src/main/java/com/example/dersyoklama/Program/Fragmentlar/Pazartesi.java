package com.example.dersyoklama.Program.Fragmentlar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dersyoklama.Program.Adapterlar.ProgramAdapter;
import com.example.dersyoklama.Program.GunAyirma;
import com.example.dersyoklama.Program.programDers;
import com.example.dersyoklama.R;
import com.example.dersyoklama.devamsizlikDers;

import java.util.ArrayList;

public class Pazartesi extends Fragment {
    private View rootView;
    private RecyclerView recyclerPazartesi;
    private ProgramAdapter programAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView =inflater.inflate(R.layout.fragment_pazartesi, container, false);


        recyclerPazartesi =rootView.findViewById(R.id.recyPazartesi);


        GunAyirma gunAyirma= new GunAyirma(getActivity(),"Pazartesi");






        recyclerPazartesi.setLayoutManager(new LinearLayoutManager(getContext()));
        programAdapter = new ProgramAdapter(gunAyirma.listeCevir(),getLayoutInflater(),getContext());
        recyclerPazartesi.setAdapter(programAdapter);



        return rootView;
    }
}
