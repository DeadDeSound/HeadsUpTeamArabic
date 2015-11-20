package com.example.nezarsaleh.headsup1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class VersusTes extends AppCompatActivity implements View.OnClickListener {


    RelativeLayout Home_Realative;
    RelativeLayout All_Relative;
    RelativeLayout Favorites_Relative;
    RelativeLayout New_Relative;
    RelativeLayout Custom_Relative;
    RelativeLayout Settings_Relative;
    RelativeLayout Store_Realtive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versus_tes);



        Home_Realative = (RelativeLayout) findViewById(R.id.Home_Realative);
        All_Relative = (RelativeLayout) findViewById(R.id.All_Relative);
        Favorites_Relative = (RelativeLayout) findViewById(R.id.Favorites_Relative);
        New_Relative = (RelativeLayout) findViewById(R.id.New_Relative);
        Custom_Relative = (RelativeLayout) findViewById(R.id.Custom_Relative);
        Settings_Relative = (RelativeLayout) findViewById(R.id.Settings_Relative);
        Store_Realtive = (RelativeLayout) findViewById(R.id.Store_Realtive);

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
