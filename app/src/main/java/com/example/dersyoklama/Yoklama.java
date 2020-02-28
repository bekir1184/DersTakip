package com.example.dersyoklama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dersyoklama.DevamsizlikAdapter;
import com.example.dersyoklama.R;
import com.example.dersyoklama.devamsizlikDers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Yoklama extends Fragment {

    private View RootView;
    RecyclerView recyclerView;
    DevamsizlikAdapter devamsizlikAdapter;
    ArrayList<devamsizlikDers> list ;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView =  inflater.inflate(R.layout.fragment_yoklama, container, false);
        recyclerView = RootView.findViewById(R.id.recView);

        fragmentManager=getActivity().getSupportFragmentManager();

        list =new ArrayList<>();

        try{
            FileInputStream fileInputStream = getActivity().openFileInput("Dersler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            while (line!=null){
               String Dizi[]=line.split("/");
               list.add(new devamsizlikDers(Dizi[0],Dizi[1],Dizi[2]));

               line=bufferedReader.readLine();
            }


        }
        catch (Exception e){

        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        devamsizlikAdapter = new DevamsizlikAdapter(getActivity(),list,getLayoutInflater(),getContext(),fragmentManager);
        recyclerView.setAdapter(devamsizlikAdapter);



        return RootView;
    }
}
