package com.example.dersyoklama;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdepter  extends PagerAdapter {
    private List<pagerModel>liste;
    private LayoutInflater layoutInflater;
    private Context context;

    public ViewPagerAdepter(List<pagerModel> liste, Context context) {
        this.liste = liste;
        this.context = context;
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.pageritem,container,false);
        TextView baslik;
        ListView listView;
        baslik=view.findViewById(R.id.pageritemBaslik);
        listView=view.findViewById(R.id.listView);

        baslik.setText(liste.get(position).getBaslik());


        container.addView(view,0);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

}
