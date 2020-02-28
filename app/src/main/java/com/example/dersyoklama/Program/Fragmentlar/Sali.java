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



public class Sali extends Fragment {
    private View rootView;
    private RecyclerView recyclerSali;
    private ProgramAdapter programAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView =inflater.inflate(R.layout.fragment_sali, container, false);


        recyclerSali =rootView.findViewById(R.id.recySali);


        GunAyirma gunAyirma= new GunAyirma(getActivity(),"Sali");






        recyclerSali.setLayoutManager(new LinearLayoutManager(getContext()));
        programAdapter = new ProgramAdapter(gunAyirma.listeCevir(),getLayoutInflater(),getContext());
        recyclerSali.setAdapter(programAdapter);



        return rootView;
    }

}
