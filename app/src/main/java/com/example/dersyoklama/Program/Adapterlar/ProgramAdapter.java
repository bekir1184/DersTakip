package com.example.dersyoklama.Program.Adapterlar;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dersyoklama.Program.programDers;
import com.example.dersyoklama.R;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {

    private List<programDers> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProgramAdapter(List<programDers> list, LayoutInflater layoutInflater, Context context) {
        this.list = list;
        this.layoutInflater = layoutInflater;
        this.context = context;
    }


    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.dersprogitem,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProgramAdapter.ViewHolder holder, int position) {
        String saat =list.get(position).saat;
        holder.saat.setText(saat);

        String dersAdi =list.get(position).dersAdi;
        holder.dersAdi.setText(dersAdi);

        holder.button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView saat,dersAdi,kalanHak;
        CardView cardView;
        Button button;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            saat =itemView.findViewById(R.id.Saat);
            dersAdi=itemView.findViewById(R.id.ders_adi);
            button=itemView.findViewById(R.id.arkaButton);
            cardView=itemView.findViewById(R.id.card);

        }
    }
}
