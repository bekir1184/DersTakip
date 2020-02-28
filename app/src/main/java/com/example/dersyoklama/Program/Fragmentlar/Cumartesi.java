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
import com.example.dersyoklama.R;


public class Cumartesi extends Fragment {

    private View rootView;
    private RecyclerView recyclerCumartesi;
    private ProgramAdapter programAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_cumartesi, container, false);


        recyclerCumartesi =rootView.findViewById(R.id.recyCumartesi);


        GunAyirma gunAyirma= new GunAyirma(getActivity(),"Cumartesi");






        recyclerCumartesi.setLayoutManager(new LinearLayoutManager(getContext()));
        programAdapter = new ProgramAdapter(gunAyirma.listeCevir(),getLayoutInflater(),getContext());
        recyclerCumartesi.setAdapter(programAdapter);



        return rootView;
    }


}
