package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuickPlay extends AppCompatActivity{

    private static final Random RANDOM = new Random();
    Button correct, pass;
    TextView result,introText;
    String[] HELLOS = {
            "Hello",
            "Salutations",
            "Greetings",
            "Howdy",
            "What's crack-a-lacking?",
            "That explains the stench!"
    };

    RelativeLayout main_relative,intro_relative;
    Boolean result_shown = false;
    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 500);
        }
    };

    private class counterDown extends CountDownTimer{

        public counterDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            timerTextView.setText(hms);
        }

        @Override
        public void onFinish() {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        Intent in = getIntent();

        correct = (Button) findViewById(R.id.correct_btn);
        pass = (Button) findViewById(R.id.pass_btn);
        result = (TextView) findViewById(R.id.play_text);
        main_relative = (RelativeLayout) findViewById(R.id.qplay_relative);
        intro_relative = (RelativeLayout) findViewById(R.id.intro);
        timerTextView = (TextView) findViewById(R.id.timer_tv);
        introText = (TextView) findViewById(R.id.intro_text);
        main_relative.setBackgroundResource(android.R.color.holo_blue_light);

        result.setVisibility(View.INVISIBLE);
        introText.setText("Get Ready !!");

        timerTextView.setText("00:03:00");
        final counterDown timer = new counterDown(180000,1000);
        timer.start();

//        startTime = System.currentTimeMillis();
//        timerHandler.postDelayed(timerRunnable, 0);

        Thread thread = new Thread() {
            @Override
                public void run() {
                    try {
                        synchronized (this) {
                            wait(5000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(HELLOS[RANDOM.nextInt(HELLOS.length)]);
                                    intro_relative.setVisibility(View.INVISIBLE);
                                    introText.setVisibility(View.INVISIBLE);
                                    result.setVisibility(View.VISIBLE);
//                                    startTime = System.currentTimeMillis();
//                                    timerHandler.postDelayed(timerRunnable, 0);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        thread.start();

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
//                                            startTime = System.currentTimeMillis();
//                                            timerHandler.postDelayed(timerRunnable, 0);
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
//                                            startTime = System.currentTimeMillis();
//                                            timerHandler.postDelayed(timerRunnable, 0);
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
        int helloLength = HELLOS.length;
            main_relative.setBackgroundResource(android.R.color.holo_blue_light);
            result_shown = false;
            return HELLOS[RANDOM.nextInt(helloLength)];
    }

}

