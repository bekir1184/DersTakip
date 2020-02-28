package com.example.dersyoklama;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DialogAyrıntı extends DialogFragment {

    private EditText dersAdi,devamHak,yapilan;



    private String dersAdistr,devamHakstr,yapilanstr;

    public DialogAyrıntı(String dersAdistr, String devamHakstr, String yapilanstr) {
        this.dersAdistr = dersAdistr;
        this.devamHakstr = devamHakstr;
        this.yapilanstr = yapilanstr;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view)
                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String dAdi=dersAdi.getText().toString();
                        String dDevam=devamHak.getText().toString();
                        String kHak=yapilan.getText().toString();

                        dersGuncelle(dAdi,dDevam,kHak);


                    }
                });



        dersAdi=view.findViewById(R.id.diaDersAdiEditText);
        devamHak=view.findViewById(R.id.diaDersDevamEditText);


        yapilan =view.findViewById(R.id.diaYapilan);


        dersAdi.setText(dersAdistr);
        devamHak.setText(devamHakstr);
        yapilan.setText(yapilanstr);



                return builder.create();
    }


    private void dersGuncelle(String dAdi, String dDevam, String kHak) {
        FileOutputStream fileOutputStream;
        String Ders[]=null;
        File file = new File("/data/data/com.example.dersyoklama/files/Dersler.txt");
        LinkedList list = new LinkedList();
        boolean bulundu=false;
        try{

            FileInputStream fileInputStream = getContext().openFileInput("Dersler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            int bulunanSatir=0;
            String line =bufferedReader.readLine();
            while (line!=null){
                String Dizi[]=line.split("/");
                list.add(line);
                if(Dizi[0].equals(dAdi)){
                    list.remove(bulunanSatir);
                    Ders=Dizi;
                    bulundu=true;
                }
                bulunanSatir++;
                line=bufferedReader.readLine();
            }

            if(bulundu){
                file.delete();
                Ders[0]=dAdi;
                Ders[1]=dDevam;
                Ders[2]= kHak;
                fileOutputStream =getContext().openFileOutput("Dersler.txt", Context.MODE_APPEND);

                for (int i=0 ;i<Ders.length;i++){
                    fileOutputStream.write(Ders[i].getBytes());
                    fileOutputStream.write("/".getBytes());


                }
                fileOutputStream.write("\n".getBytes());

                for (int i=0;i<list.size();i++){
                    String yazilacak= (String) list.get(i);
                    fileOutputStream.write(yazilacak.getBytes());
                    fileOutputStream.write("\n".getBytes());

                }

                fileOutputStream.close();


            }

            bufferedReader.close();
        }
        catch (Exception e){

        }


    }

}
