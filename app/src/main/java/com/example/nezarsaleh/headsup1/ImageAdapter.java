package com.example.nezarsaleh.headsup1;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflater;
    ArrayList<CategoryDataModel> cat;

    // Constructor
    public ImageAdapter(Context c, ArrayList<CategoryDataModel> cat){
        mContext = c;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView picture;
        TextView name;

        if(v == null)
        {
            v = inflater.inflate(R.layout.grid_item,parent,false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView)v.findViewById(R.id.picture);
        name = (TextView)v.findViewById(R.id.text);

        picture.setImageResource(cat.get(position).getCatImageResource());
        name.setText(cat.get(position).getCatName());

        return v;

    }
}