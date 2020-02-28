package com.example.dersyoklama;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dersyoklama.R;
import com.example.dersyoklama.TarihAyarla;


public class DersEkle extends Fragment {
    private View rootView;
    private static EditText dersAdi;
    private static EditText dersDevamHak;
    private static EditText dersSaat;
    private Button ekleButton;


    public static int  getDersDevamHak() {
        return Integer.parseInt(String.valueOf(dersDevamHak.getText()));
    }

    public static String getDersAdi() {
        return String.valueOf(dersAdi.getText());
    }

    public static int getDersSaat() {
        return Integer.parseInt(String.valueOf(dersSaat.getText()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Hatalı Giriş");
        builder.setMessage("Ders saati en az 1 olmalıdır!!!");
        builder.setNegativeButton("TAMAM", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        final AlertDialog.Builder hata = new AlertDialog.Builder(getContext());
        builder.setTitle("Eksik Bilgi");
        builder.setMessage("Lütfen tüm alanları dolduruuz");
        builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_ders_ekle, container, false);
        dersAdi=rootView.findViewById(R.id.dersAdiEditText);
        dersDevamHak=rootView.findViewById(R.id.devamHakEditText);
        dersSaat=rootView.findViewById(R.id.dersSaatEditText);

        ekleButton =(Button)rootView.findViewById(R.id.ekleButton);

        ekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dersSaatstr=dersSaat.getText().toString();

                if(!TextUtils.isEmpty(dersSaatstr)){
                    if(Integer.parseInt(String.valueOf(dersSaatstr))<1){
                        builder.show();
                    }
                    else if(Integer.parseInt(String.valueOf(dersSaat.getText()))>=1){
                        Intent intent = new Intent(getActivity(), TarihAyarla.class);
                        startActivity(intent);

                    }
                    else{
                        hata.show();
                    }

                }
                else {
                    builder.show();

                }


            }
        });




        return rootView;
    }

}
