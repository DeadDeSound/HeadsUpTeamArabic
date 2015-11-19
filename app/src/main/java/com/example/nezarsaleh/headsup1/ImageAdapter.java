package com.example.nezarsaleh.headsup1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    ArrayList imagePath;

    // Constructor
    public ImageAdapter(Context c, ArrayList imagePath){
        mContext = c;
        this.imagePath = imagePath;
    }

    @Override
    public int getCount() {
        return imagePath.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePath.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SquareImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new SquareImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(185, 104));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (SquareImageView) convertView;
        }
            String url = new Core(mContext).large_image_url + imagePath.get(position) ;
            Picasso.with(mContext).load(url).into(imageView);
        return imageView;

    }
}