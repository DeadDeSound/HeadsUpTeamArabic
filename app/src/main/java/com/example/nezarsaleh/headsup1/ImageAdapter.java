package com.example.nezarsaleh.headsup1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflater;
    ArrayList<CategoryDataModel> cat;
    int favFragment;

    // Constructor
    public ImageAdapter(Context c, ArrayList<CategoryDataModel> cat,int favFragment){
        mContext = c;
        this.favFragment = favFragment;
        this.cat = cat;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return cat.size();
    }

    @Override
    public Object getItem(int position) {
        return cat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final ImageView picture,fav;
        TextView name;

        if(v == null)
        {
            v = inflater.inflate(R.layout.grid_item,parent,false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
            v.setTag(R.id.fav_image, v.findViewById(R.id.fav_image));
        }

        picture = (ImageView)v.findViewById(R.id.picture);
        fav = (ImageView)v.findViewById(R.id.fav_image);
        name = (TextView)v.findViewById(R.id.text);

        picture.setImageResource(cat.get(position).getCatImageResource());
        name.setText(cat.get(position).getCatName());

        if (cat.get(position).getFav() == 1){
            fav.setImageResource(R.drawable.heart_red);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int favIndicator = cat.get(position).getFav();
                DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
                if (favIndicator == 0){
                    int id = cat.get(position).getID();
                    Log.d("Fav ID", String.valueOf(id));
                    databaseHelper.updateFav(id, 1);
                    cat.get(position).setFav(1);
                    fav.setImageResource(R.drawable.heart_red);
                }else if (favIndicator == 1){
                    int id = cat.get(position).getID();
                    Log.d("Fav ID", String.valueOf(id));
                    databaseHelper.updateFav(id, 0);
                    cat.get(position).setFav(0);
                    fav.setImageResource(R.drawable.heart_white);
                    if (favFragment == 1) {
                        cat.remove(position);
                        updateResults(cat);
                    }
                }
            }
        });
        return v;
    }

    public void updateResults(ArrayList<CategoryDataModel> results) {
        cat = results;
        notifyDataSetChanged();
    }
}