package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class QuickPlay extends AppCompatActivity{

    private static final Random RANDOM = new Random();
    Button correct, pass;
    TextView result;
    String[] HELLOS = {
            "Hello",
            "Salutations",
            "Greetings",
            "Howdy",
            "What's crack-a-lacking?",
            "That explains the stench!"
    };

    RelativeLayout main_relative;
    Boolean result_shown = false;
    String gameName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        Intent in = getIntent();


        correct = (Button) findViewById(R.id.correct_btn);
        pass = (Button) findViewById(R.id.pass_btn);
        result = (TextView) findViewById(R.id.play_text);
        main_relative = (RelativeLayout) findViewById(R.id.qplay_relative);

        result.setText(HELLOS[RANDOM.nextInt(HELLOS.length)]);

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Correct");
                main_relative.setBackgroundResource(android.R.color.holo_green_light);
                if (!result_shown) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            result_shown = true;
                            try {
                                synchronized (this) {
                                    wait(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(sayHello());
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Pass");
                main_relative.setBackgroundResource(android.R.color.holo_red_light);
                if (!result_shown) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            result_shown = true;
                            try {
                                synchronized (this) {
                                    wait(1000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            result.setText(sayHello());
                                        }
                                    });
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }
            }
        });
    }


    private String sayHello() {
        // Select a random hello.
        switch (gameName){
        case "Films": int helloLength = HELLOS.length;
            main_relative.setBackgroundResource(android.R.color.holo_blue_light);
            result_shown = false;
            return HELLOS[RANDOM.nextInt(helloLength)];
        }
        return "Error";
    }

}

