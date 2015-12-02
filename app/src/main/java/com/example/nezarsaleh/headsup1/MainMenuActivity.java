package com.example.nezarsaleh.headsup1;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainMenuActivity extends FragmentActivity implements View.OnClickListener {

    RelativeLayout Home_Relative;
    RelativeLayout All_Relative;
    RelativeLayout Favorites_Relative;
    RelativeLayout New_Relative;
    RelativeLayout Custom_Relative;
    RelativeLayout Settings_Relative;
    RelativeLayout Store_Relative;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Home_Relative = (RelativeLayout) findViewById(R.id.Home_Realative);
        All_Relative = (RelativeLayout) findViewById(R.id.All_Relative);
        Favorites_Relative = (RelativeLayout) findViewById(R.id.Favorites_Relative);
        New_Relative = (RelativeLayout) findViewById(R.id.New_Relative);
        Custom_Relative = (RelativeLayout) findViewById(R.id.Custom_Relative);
        Settings_Relative = (RelativeLayout) findViewById(R.id.Settings_Relative);
        Store_Relative = (RelativeLayout) findViewById(R.id.Store_Realtive);

        All_Relative.setBackgroundColor(Color.WHITE);

        All_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
        New_Relative.setOnClickListener(this);
        Custom_Relative.setOnClickListener(this);
        Settings_Relative.setOnClickListener(this);
        Store_Relative.setOnClickListener(this);
        Home_Relative.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_All fragment = new Fragment_All();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

        if (v == Favorites_Relative) {
            Favorites_Relative.setBackgroundResource(R.color.white);

            All_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment_Favorites fragment = new Fragment_Favorites();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }


        if (v == All_Relative) {

            All_Relative.setBackgroundResource(R.color.white);

            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment_All fragment = new Fragment_All();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

        if (v == New_Relative) {
            New_Relative.setBackgroundResource(R.color.white);

            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);
        }

        if (v == Custom_Relative) {
            Custom_Relative.setBackgroundResource(R.color.white);

            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            Fragment_Custom fragment = new Fragment_Custom();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        if (v==Settings_Relative){
            Settings_Relative.setBackgroundResource(R.color.white);

            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);
        }

        if (v== Store_Relative){
            Store_Relative.setBackgroundResource(R.color.white);

            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
        }

        if (v== Home_Relative){
            finish();
        }
    }
}
