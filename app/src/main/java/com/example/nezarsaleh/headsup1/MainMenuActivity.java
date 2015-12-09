package com.example.nezarsaleh.headsup1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout Home_Relative;
    RelativeLayout All_Relative;
    RelativeLayout Favorites_Relative;
//    RelativeLayout New_Relative;
    RelativeLayout Custom_Relative;
    RelativeLayout Settings_Relative;
    RelativeLayout Store_Relative;

    TextView CoinsTxt;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public void setCustomFlag(int customFlag) {
        CustomFlag = customFlag;
    }

    int CustomFlag = 0;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Home_Relative = (RelativeLayout) findViewById(R.id.Home_Realative);
        All_Relative = (RelativeLayout) findViewById(R.id.All_Relative);
        Favorites_Relative = (RelativeLayout) findViewById(R.id.Favorites_Relative);
//        New_Relative = (RelativeLayout) findViewById(R.id.New_Relative);
        Custom_Relative = (RelativeLayout) findViewById(R.id.Custom_Relative);
        Settings_Relative = (RelativeLayout) findViewById(R.id.Settings_Relative);
        Store_Relative = (RelativeLayout) findViewById(R.id.Store_Realtive);

        CoinsTxt = (TextView) findViewById(R.id.Coins);

        All_Relative.setBackgroundColor(Color.WHITE);

        All_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
        Favorites_Relative.setOnClickListener(this);
//        New_Relative.setOnClickListener(this);
        Custom_Relative.setOnClickListener(this);
        Settings_Relative.setOnClickListener(this);
        Store_Relative.setOnClickListener(this);
        Home_Relative.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void updateCoins(int Coins) {
        CoinsTxt = (TextView) findViewById(R.id.Coins);
        CoinsTxt.setText("( " + Coins + " $ )");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", 0);
        int Coins = pref.getInt("Coins", -1);
        if (Coins != -1) {
            CoinsTxt.setText("( " + Coins + " $ )");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment_All fragment = new Fragment_All();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

        if (v == Favorites_Relative) {
            Favorites_Relative.setBackgroundResource(R.color.white);

            All_Relative.setBackgroundResource(R.color.menu_item_off);
//            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            if (CustomFlag == 0) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Favorites fragment = new Fragment_Favorites();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("ARE YOU SURE?");
                alertDialog.setMessage("all your unsaved data will be erased, OK ?");
                alertDialog.setIcon(R.drawable.card);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                fragmentManager = getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_Favorites fragment = new Fragment_Favorites();
                                fragmentTransaction.replace(R.id.fragment, fragment);
                                fragmentTransaction.commit();
                                setCustomFlag(0);
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }


        if (v == All_Relative) {

            All_Relative.setBackgroundResource(R.color.white);

            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
//            New_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            if (CustomFlag == 0) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_All fragment = new Fragment_All();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("ARE YOU SURE?");
                alertDialog.setMessage("all your unsaved data will be erased, OK ?");
                alertDialog.setIcon(R.drawable.card);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                fragmentManager = getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_All fragment = new Fragment_All();
                                fragmentTransaction.replace(R.id.fragment, fragment);
                                fragmentTransaction.commit();
                                setCustomFlag(0);
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        }

//        if (v == New_Relative) {
//            New_Relative.setBackgroundResource(R.color.white);
//
//            All_Relative.setBackgroundResource(R.color.menu_item_off);
//            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
//            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
//            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
//            Store_Relative.setBackgroundResource(R.color.menu_item_off);
//        }

        if (v == Custom_Relative) {
            Custom_Relative.setBackgroundResource(R.color.white);

//            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            if (CustomFlag == 0) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Fragment_Custom fragment = new Fragment_Custom();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
            }else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("ARE YOU SURE?");
                alertDialog.setMessage("all your unsaved data will be erased, OK ?");
                alertDialog.setIcon(R.drawable.card);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                fragmentManager = getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_Custom fragment = new Fragment_Custom();
                                fragmentTransaction.replace(R.id.fragment, fragment);
                                fragmentTransaction.commit();
                                setCustomFlag(0);
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                        }
                    });
                alertDialog.show();
            }
        }

        if (v == Settings_Relative) {
            Settings_Relative.setBackgroundResource(R.color.white);

            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
//            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
            Store_Relative.setBackgroundResource(R.color.menu_item_off);

            final Dialog dialog = new Dialog(MainMenuActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.setting_dialog);
            final Button btn60 = (Button) dialog.findViewById(R.id.btn60);
            final Button btn90 = (Button) dialog.findViewById(R.id.btn90);
            final Button btn120 = (Button) dialog.findViewById(R.id.btn120);

            final SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", 0);
            final SharedPreferences.Editor editor = pref.edit();

            int time = pref.getInt("Time",60);

            if (time == 60000){
                btn60.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btn90.setBackgroundColor(getResources().getColor(R.color.menu_color));
                btn120.setBackgroundColor(getResources().getColor(R.color.menu_color));
            }else if (time == 90000){
                btn60.setBackgroundColor(getResources().getColor(R.color.menu_color));
                btn90.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btn120.setBackgroundColor(getResources().getColor(R.color.menu_color));
            }else if (time == 120000){
                btn60.setBackgroundColor(getResources().getColor(R.color.menu_color));
                btn90.setBackgroundColor(getResources().getColor(R.color.menu_color));
                btn120.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            btn60.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn60.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btn90.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    btn120.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    editor.putInt("Time", 60000);
                    editor.apply();
                    dialog.dismiss();
                }
            });

            btn90.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn60.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    btn90.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    btn120.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    editor.putInt("Time", 90000);
                    editor.apply();
                    dialog.dismiss();
                }
            });

            btn120.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn60.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    btn90.setBackgroundColor(getResources().getColor(R.color.menu_color));
                    btn120.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    editor.putInt("Time", 120000);
                    editor.apply();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        if (v == Store_Relative) {
            Store_Relative.setBackgroundResource(R.color.white);

            Settings_Relative.setBackgroundResource(R.color.menu_item_off);
            Custom_Relative.setBackgroundResource(R.color.menu_item_off);
//            New_Relative.setBackgroundResource(R.color.menu_item_off);
            All_Relative.setBackgroundResource(R.color.menu_item_off);
            Favorites_Relative.setBackgroundResource(R.color.menu_item_off);
        }

        if (v == Home_Relative) {
            if (CustomFlag == 0) {
                finish();
            }else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("ARE YOU SURE?");
                alertDialog.setMessage("all your unsaved data will be erased, OK ?");
                alertDialog.setIcon(R.drawable.card);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                setCustomFlag(0);
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    }

}
