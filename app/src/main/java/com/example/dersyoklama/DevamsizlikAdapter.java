package com.example.dersyoklama;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class DevamsizlikAdapter extends RecyclerView.Adapter<DevamsizlikAdapter.ViewHolder>  {

    private List<devamsizlikDers> list;
    private LayoutInflater layoutInflater;
    private Context context;
    FragmentManager fragmentManeger;
    Activity activity;

    public DevamsizlikAdapter(Activity activity,List<devamsizlikDers> list, LayoutInflater layoutInflater, Context context,FragmentManager fragmentManager) {
        this.activity=activity;
        this.list = list;
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.fragmentManeger=fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =layoutInflater.inflate(R.layout.devamsizlik_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        String baslik =list.get(position).getDersAdi();
        holder.BaslikDevam.setText(baslik);

        String devamHak=list.get(position).getDevamHak();
        holder.DHak.setText(devamHak);

        String kalanHak=list.get(position).getKalanHak();
        holder.kalanHak.setText(kalanHak);
            //Ders silme
        holder.silBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dersSil(list.get(position).getDersAdi());
                programdanSil(list.get(position).getDersAdi());
                yenile();


            }
        });
        holder.ayrıntı.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAc(list.get(position).getDersAdi(),list.get(position).getDevamHak(),list.get(position).getKalanHak());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView BaslikDevam,DHak,kalanHak;
        Button silBtn,ayrıntı;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BaslikDevam=itemView.findViewById(R.id.BaslikDevam);
            DHak=itemView.findViewById(R.id.DHak);
            kalanHak=itemView.findViewById(R.id.kalanHak);
            silBtn=itemView.findViewById(R.id.sil);
            ayrıntı=itemView.findViewById(R.id.ayrıntılar);


        }
    }
    public void dersSil(String dersAdi){
        FileOutputStream fileOutputStream;
        File file = new File("/data/data/com.example.dersyoklama/files/Dersler.txt");
        LinkedList list= new LinkedList();
        boolean bulundu=false;

        try{

            FileInputStream fileInputStream = context.openFileInput("Dersler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            int bulunanSatir=0;
            String line =bufferedReader.readLine();
            while (line!=null){
                String Dizi[]=line.split("/");
                list.add(line);
                if(Dizi[0].equals(dersAdi)){
                    bulundu=true;
                    list.remove(bulunanSatir);

                }
                bulunanSatir++;
                line=bufferedReader.readLine();
            }

            file.delete();

            if(bulundu){
                fileOutputStream =context.openFileOutput("Dersler.txt", Context.MODE_APPEND);

                for (int i=0;i<bulunanSatir;i++){
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
    //calismiyor
    public void programdanSil(String dersAdi){
        File ayri = new File("/data/data/com.example.dersyoklama/files/AyriDersler.txt");
        FileOutputStream fileOutputStream;
        LinkedList list= new LinkedList();
        boolean bulundu=false;

        try{

            FileInputStream fileInputStream = context.openFileInput("AyriDersler.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);

            String line =bufferedReader.readLine();
            while (line!=null){
                String Dizi[]=line.split("/");

                if(Dizi[0].equals(dersAdi)){
                    bulundu=true;


                }
                else{
                    list.add(line);
                }

                line=bufferedReader.readLine();
            }

            ayri.delete();

            if(bulundu){
                fileOutputStream =context.openFileOutput("AyriDersler.txt", Context.MODE_APPEND);

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
    public void yenile(){
        FragmentTransaction fragmentTransaction =fragmentManeger.beginTransaction();
        fragmentTransaction.replace(R.id.frameLay,new Yoklama());
        fragmentTransaction.commit();

    }
    public void dialogAc(String dersAdi,String dHak,String dYapilan){
        DialogAyrıntı dialogAyrıntı= new DialogAyrıntı(dersAdi,dHak,dYapilan);
        dialogAyrıntı.show(fragmentManeger,"Dialog");

    }

}
