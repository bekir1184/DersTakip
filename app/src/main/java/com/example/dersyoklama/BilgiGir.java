package com.example.dersyoklama;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BilgiGir extends AppCompatActivity {
    boolean bulundu=false;
    String Ders[]=null;
    Button girdimB;
    Button girmedimB;
    TextView textView;
    FileOutputStream fileOutputStream;
    LinkedList list;
    String dersAdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgi_gir);


        girdimB =(Button)findViewById(R.id.girdimButton);
        girmedimB=(Button)findViewById(R.id.girmedimButton);
        textView=findViewById(R.id.girdinMiBaslik);

        Bundle bundle =getIntent().getExtras();
        dersAdi=bundle.getString("dersAdi");

        textView.setText(dersAdi);

        girdimB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        girmedimB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                File file = new File("/data/data/com.example.dersyoklama/files/Dersler.txt");
                list = new LinkedList();
                try{

                    FileInputStream fileInputStream = openFileInput("Dersler.txt");
                    InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                    String line =bufferedReader.readLine();
                    while (line!=null){
                        String Dizi[]=line.split("/");

                        if(Dizi[0].equals(dersAdi)){
                            bulundu=true;
                            Ders=Dizi;


                        }
                        else{
                            list.add(line);
                        }

                        line=bufferedReader.readLine();
                    }
                    file.delete();
                    if(bulundu){

                        Ders[2]= String.valueOf(Integer.parseInt(Ders[2])+1);
                        fileOutputStream =openFileOutput("Dersler.txt", Context.MODE_APPEND);

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
                System.exit(0);

            }
        });





    }
}
