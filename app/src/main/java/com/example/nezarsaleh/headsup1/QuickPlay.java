package com.example.nezarsaleh.headsup1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Handler;

public class QuickPlay extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    Button correct, pass;
    TextView result;
    String[] HELLOS = {
            "Hello",
            "Salutations",
            "Greetings",
            "Howdy",
            "What's crack-a-lackin?",
            "That explains the stench!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        correct = (Button) findViewById(R.id.correct_btn);
        pass = (Button) findViewById(R.id.pass_btn);
        result = (TextView) findViewById(R.id.play_text);

        result.setText(HELLOS[RANDOM.nextInt(HELLOS.length)]);

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Correct");

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Vector<String> alreadyUsed = new Vector<>();
                                        for (String v : HELLOS) {
                                            String nextString;
                                            do {
                                                nextString = HELLOS[RANDOM.nextInt(HELLOS.length)];
                                            } while (alreadyUsed.contains(nextString));
                                            alreadyUsed.add(nextString);
                                            result.setText(nextString);
                                        }
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

        });
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setVisibility(View.VISIBLE);
                result.setText("Pass");
                Thread thread = new Thread() {
                    @Override
                    public void run() {
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

        });
    }


    private String sayHello() {
        // Select a random hello.
        int helloLength = HELLOS.length;
        return HELLOS[RANDOM.nextInt(helloLength)];
    }
}

