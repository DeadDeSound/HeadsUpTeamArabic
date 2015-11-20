package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout Quick_Play_Relative;
    RelativeLayout versus_relative;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        Quick_Play_Relative= (RelativeLayout) findViewById(R.id.relative_quick);
        versus_relative = (RelativeLayout) findViewById(R.id.versus_relative);
        Quick_Play_Relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Main2Activity.class);
                startActivity(intent);
            }
        });

        versus_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(),Versus_Test_2.class);
                startActivity(intent);

            }
        });



    }
}
