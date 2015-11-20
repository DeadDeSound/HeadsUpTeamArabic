package com.example.nezarsaleh.headsup1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Versus_Test_2 extends AppCompatActivity {

    TextView reg_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versus__test_2);
        reg_user = (TextView) findViewById(R.id.reg_user);

        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        First_Fr_1 first_fr_1 =  new First_Fr_1();
        fragmentTransaction.add(R.id.Fragment_Container_1,first_fr_1);
        fragmentTransaction.commit();



        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                Second_Fr_2  second_fr_2 = new Second_Fr_2();
                fragmentTransaction1.replace(R.id.Fragment_Container_1,second_fr_2);
                fragmentTransaction1.commit();
            }
        });

    }
}
