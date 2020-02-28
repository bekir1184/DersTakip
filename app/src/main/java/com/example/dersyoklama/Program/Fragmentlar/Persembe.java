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

public class Persembe extends Fragment {
    private View rootView;
    private RecyclerView recyclerPersembe;
    private ProgramAdapter programAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_persembe, container, false);


        recyclerPersembe =rootView.findViewById(R.id.recyPersembe);


        GunAyirma gunAyirma= new GunAyirma(getActivity(),"Persembe");






        recyclerPersembe.setLayoutManager(new LinearLayoutManager(getContext()));
        programAdapter = new ProgramAdapter(gunAyirma.listeCevir(),getLayoutInflater(),getContext());
        recyclerPersembe.setAdapter(programAdapter);



        return rootView;
    }
}
