package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends FragmentActivity implements View.OnClickListener {

    RelativeLayout Home_Realative;
    RelativeLayout All_Relative;
    RelativeLayout Favorites_Relative;
    RelativeLayout New_Relative;
    RelativeLayout Custom_Relative;
    RelativeLayout Settings_Relative;
    RelativeLayout Store_Realtive;
    GridView Decks_Grid;
    ArrayList imagePath = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Home_Realative = (RelativeLayout) findViewById(R.id.Home_Realative);
        All_Relative = (RelativeLayout) findViewById(R.id.All_Relative);
        Favorites_Relative = (RelativeLayout) findViewById(R.id.Favorites_Relative);
        New_Relative = (RelativeLayout) findViewById(R.id.New_Relative);
        Custom_Relative = (RelativeLayout) findViewById(R.id.Custom_Relative);
        Settings_Relative = (RelativeLayout) findViewById(R.id.Settings_Relative);
        Store_Realtive = (RelativeLayout) findViewById(R.id.Store_Realtive);
        Decks_Grid = (GridView) findViewById(R.id.dick_grid);

        new loadingData().execute();

        Intent Intent = getIntent();

        All_Relative.setBackgroundColor(Color.WHITE);

        All_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
        New_Relative.setOnClickListener(this);
        Custom_Relative.setOnClickListener(this);
        Settings_Relative.setOnClickListener(this);
        Store_Realtive.setOnClickListener(this);
        Home_Realative.setOnClickListener(this);







    }

    private class loadingData extends AsyncTask {

        JSONArray results;

        @Override
        protected void onPostExecute(Object o) {
            if (imagePath == null){
                Toast.makeText(getBaseContext(), "No Data", Toast.LENGTH_SHORT).show();
            }else {
                Decks_Grid.setAdapter(new ImageAdapter(Main2Activity.this, imagePath));
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Core core = new Core(Main2Activity.this);
            try {
                String data = core.getMovies().toString();
                if (data != null){
                    results = core.getMovies().getJSONArray("results");
                    for (int i = 0; i < results.length();i++){
                        JSONObject movie = results.getJSONObject(i);
                        String posterPath = movie.getString("backdrop_path");
                        Log.d("posterPath", posterPath);
                        imagePath.add(posterPath);
                    }
                    Log.d("data", data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == Favorites_Relative) {
            Favorites_Relative.setBackgroundColor(Color.WHITE);

            All_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Realtive.setBackgroundResource(R.color.menu_item_off);

        }


        if (v == All_Relative) {

            All_Relative.setBackgroundColor(Color.WHITE);

            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Realtive.setBackgroundResource(R.color.menu_item_off);

        }


        if (v == New_Relative) {
            New_Relative.setBackgroundColor(Color.WHITE);

            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Realtive.setBackgroundResource(R.color.menu_item_off);


        }

        if (v == Custom_Relative) {
            Custom_Relative.setBackgroundColor(Color.WHITE);

            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Realtive.setBackgroundResource(R.color.menu_item_off);


        }


        if (v==Settings_Relative){
            Settings_Relative.setBackgroundColor(Color.WHITE);

            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Realtive.setBackgroundResource(R.color.menu_item_off);



        }

        if (v==Store_Realtive){
            Store_Realtive.setBackgroundColor(Color.WHITE);

            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);




        }


        if (v==Home_Realative){
            finish();
        }


    }
}
