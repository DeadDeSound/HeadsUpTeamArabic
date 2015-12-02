package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout Quick_Play_Relative;
    RelativeLayout versus_relative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        Quick_Play_Relative= (RelativeLayout) findViewById(R.id.relative_quick);
        versus_relative = (RelativeLayout) findViewById(R.id.versus_relative);

        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", 0);
        SharedPreferences.Editor editor = myPrefs.edit();
        if (myPrefs.getInt("Coins",-1) == -1) {
            editor.putInt("Coins", 10);
            editor.apply();
        }

        Quick_Play_Relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainMenuActivity.class);
                startActivity(intent);
            }
        });

        versus_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), Versus_Test_2.class);
                startActivity(intent);

            }
        });
    }

}
